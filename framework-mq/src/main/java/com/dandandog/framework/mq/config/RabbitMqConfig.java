package com.dandandog.framework.mq.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.common.utils.SpringContextUtil;
import com.dandandog.framework.mq.model.ImqBinding;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JohnnyLiu
 */
@Configuration
public class RabbitMqConfig implements InitializingBean {

    private final List<ImqBinding> bindings;

    @Autowired
    public RabbitMqConfig(List<ImqBinding> bindings) {
        this.bindings = bindings;
    }

    @Override
    public void afterPropertiesSet() {
        if (CollUtil.isNotEmpty(bindings)) {
            Multimap<String, ImqBinding> bindingMap = ArrayListMultimap.create();
            bindings.forEach(binding -> {
                bindingMap.put(binding.getExchange(), binding);
            });

            bindingMap.keys().forEach(exchangeName -> {
                CustomExchange customExchange = buildCustomExchange(exchangeName);
                bindingMap.get(exchangeName).forEach(binding -> {
                    Queue queue = buildPluginQueue(binding.getQueue());
                    buildPluginBinding(customExchange, queue, binding.getRouteKey());
                });
            });
        }
    }

    private CustomExchange buildCustomExchange(String exchangeName) {
        Map<String, Object> args = new HashMap<>(1);
        args.put("x-delayed-type", "direct");
        String beanName = createBeanName(exchangeName, "Exchange");
        return SpringContextUtil.registerBean(beanName, CustomExchange.class,
                () -> new CustomExchange(exchangeName, "x-delayed-message", true, false, args));
    }

    private Queue buildPluginQueue(String queueName) {
        String beanName = createBeanName(queueName, "Queue");
        return SpringContextUtil.registerBean(beanName, Queue.class,
                () -> new Queue(queueName, true));
    }

    private Binding buildPluginBinding(CustomExchange exchange, Queue queue, String routeKey) {
        String beanName = createBeanName(routeKey, "Binding");
        return SpringContextUtil.registerBean(beanName, Binding.class,
                () -> BindingBuilder.bind(queue).to(exchange).with(routeKey).noargs());
    }

    private String createBeanName(String name, String suffix) {
        String repName = StrUtil.replace(name, ".", "_");
        String suffixName = StrUtil.addSuffixIfNot(repName, suffix);
        return StrUtil.toCamelCase(suffixName);
    }
}
