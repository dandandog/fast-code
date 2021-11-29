$(function () {
    $.extend(PrimeFaces.locales['zh_CN'], {
        decimalSeparator: '.',
        groupingSeparator: ',',
        messages: messages_zh_CN
    })
    $.extend(PrimeFaces.locales['zh_TW'], {
        decimalSeparator: '.',
        groupingSeparator: ',',
        messages: messages_zh_TW
    })
})

$(document).scroll(function () {
    $(".ui-input-overlay,.ui-overlaypanel").hide()
})


function start() {
    PF('statusDialog').show();
}

function stop() {
    PF('statusDialog').hide();
}


const messages_zh_CN = {
    'javax.faces.component.UIInput.CONVERSION': '{0}：出现转换错误。',
    'javax.faces.component.UIInput.REQUIRED': '{0}：验证错误：必须输入值。',
    'javax.faces.component.UIInput.UPDATE': '{0}：处理提交的信息时出错。',
    'javax.faces.component.UISelectOne.INVALID': '{0}：验证错误：值无效',
    'javax.faces.component.UISelectMany.INVALID': '{0}：验证错误：值无效',
    'javax.faces.converter.BigDecimalConverter.DECIMAL': '{2}: \'{0}\' 必须是带符号的十进制数值。',
    'javax.faces.converter.BigDecimalConverter.DECIMAL_detail': '{2}: \'{0}\' 必须是由零个或多个数字构成的带符号十进制数值，可以后跟小数点和小数部分。示例: {1}',
    'javax.faces.converter.BigIntegerConverter.BIGINTEGER': '{2}: \'{0}\' 必须是由一个或多个数字构成的数值。',
    'javax.faces.converter.BigIntegerConverter.BIGINTEGER_detail': '{2}: \'{0}\' 必须是由一个或多个数字构成的数值。示例: {1}',
    'javax.faces.converter.BooleanConverter.BOOLEAN': '{1}: \'{0}\' 必须是 \'true\' 或 \'false\'。',
    'javax.faces.converter.BooleanConverter.BOOLEAN_detail': '{1}: \'{0}\' 必须是 \'true\' 或 \'false\'。非 \'true\' 值都将会被视为 \'false\'。',
    'javax.faces.converter.ByteConverter.BYTE': '{2}: \'{0}\' 必须是介于 -128 和 127 之间的数值。',
    'javax.faces.converter.ByteConverter.BYTE_detail': '{2}: \'{0}\' 必须是介于 -128 和 127 之间的数值。示例: {1}',
    'javax.faces.converter.CharacterConverter.CHARACTER': '{1}: \'{0}\' 必须是有效字符。',
    'javax.faces.converter.CharacterConverter.CHARACTER_detail': '{1}: \'{0}\' 必须是有效的 ASCII 字符。',
    'javax.faces.converter.DateTimeConverter.DATE': '{2}：无法将 \'{0}\' 理解为日期。',
    'javax.faces.converter.DateTimeConverter.DATE_detail': '{2}：无法将 \'{0}\' 理解为日期。示例: {1}',
    'javax.faces.converter.DateTimeConverter.TIME': '{2}：无法将 \'{0}\' 理解为时间。',
    'javax.faces.converter.DateTimeConverter.TIME_detail': '{2}：无法将 \'{0}\' 理解为时间。示例: {1}',
    'javax.faces.converter.DateTimeConverter.DATETIME': '{2}：无法将 \'{0}\' 理解为日期和时间。',
    'javax.faces.converter.DateTimeConverter.DATETIME_detail': '{2}：无法将 \'{0}\' 理解为日期和时间。示例: {1}',
    'javax.faces.converter.DateTimeConverter.PATTERN_TYPE': '{1}：必须指定 \'pattern\' 或 \'type\' 属性才能转换值 \'{0}\'。',
    'javax.faces.converter.DoubleConverter.DOUBLE': '{2}: \'{0}\' 必须是由一个或多个数字构成的数值。',
    'javax.faces.converter.DoubleConverter.DOUBLE_detail': '{2}: \'{0}\' 必须是介于 4.9E-324 和 1.7976931348623157E308 之间的数值。示例: {1}',
    'javax.faces.converter.EnumConverter.ENUM': '{2}: \'{0}\' 必须能够转换为枚举值。',
    'javax.faces.converter.EnumConverter.ENUM_detail': '{2}: \'{0}\' 必须能够从一个含有常数 \'{1}\' 的枚举值转换为另一个枚举值。',
    'javax.faces.converter.EnumConverter.ENUM_NO_CLASS': '{1}: \'{0}\' 必须能够从一个枚举值转换为另一个枚举值，但未提供枚举类。',
    'javax.faces.converter.EnumConverter.ENUM_NO_CLASS_detail': '{1}: \'{0}\' 必须能够从枚举值转换为枚举值，但未提供枚举类。',
    'javax.faces.converter.FloatConverter.FLOAT': '{2}: \'{0}\' 必须是由一个或多个数字构成的数值。',
    'javax.faces.converter.FloatConverter.FLOAT_detail': '{2}: \'{0}\' 必须是介于 1.4E-45 和 3.4028235E38 之间的数值。示例: {1}',
    'javax.faces.converter.IntegerConverter.INTEGER': '{2}: \'{0}\' 必须是由一个或多个数字构成的数值。',
    'javax.faces.converter.IntegerConverter.INTEGER_detail': '{2}: \'{0}\' 必须是介于 -2147483648 和 2147483647 之间的数值。示例: {1}',
    'javax.faces.converter.LongConverter.LONG': '{2}: \'{0}\' 必须是由一个或多个数字构成的数值。',
    'javax.faces.converter.LongConverter.LONG_detail': '{2}: \'{0}\' 必须是介于 -9223372036854775808 和 9223372036854775807 之间的数值。示例: {1}',
    'javax.faces.converter.NumberConverter.CURRENCY': '{2}：无法将 \'{0}\' 理解为货币值。',
    'javax.faces.converter.NumberConverter.CURRENCY_detail': '{2}：无法将 \'{0}\' 理解为货币值。示例: {1}',
    'javax.faces.converter.NumberConverter.PERCENT': '{2}：无法将 \'{0}\' 理解为百分数。',
    'javax.faces.converter.NumberConverter.PERCENT_detail': '{2}：无法将 \'{0}\' 理解为百分数。示例: {1}',
    'javax.faces.converter.NumberConverter.NUMBER': '{2}: \'{0}\' 不是数值。',
    'javax.faces.converter.NumberConverter.NUMBER_detail': '{2}: \'{0}\' 不是数值。示例: {1}',
    'javax.faces.converter.NumberConverter.PATTERN': '{2}: \'{0}\' 不是数值模式。',
    'javax.faces.converter.NumberConverter.PATTERN_detail': '{2}: \'{0}\' 不是数值模式。示例: {1}',
    'javax.faces.converter.ShortConverter.SHORT': '{2}: \'{0}\' 必须是由一个或多个数字构成的数值。',
    'javax.faces.converter.ShortConverter.SHORT_detail': '{2}: \'{0}\' 必须是介于 -32768 和 32767 之间的数值。示例: {1}',
    'javax.faces.converter.STRING': '{1}：无法将 \'{0}\' 转换为字符串。',
    'javax.faces.validator.DoubleRangeValidator.MAXIMUM': '{1}：验证错误：值大于允许的最大值 \'{0}\'',
    'javax.faces.validator.DoubleRangeValidator.MINIMUM': '{1}：验证错误：值小于允许的最小值 \'{0}\'',
    'javax.faces.validator.DoubleRangeValidator.NOT_IN_RANGE': '{2}：验证错误：指定的属性不在预期值 {0} 和 {1} 之间。',
    'javax.faces.validator.DoubleRangeValidator.TYPE': '{0}：验证错误：值不是正确的类型',
    'javax.faces.validator.LengthValidator.MAXIMUM': '{1}：验证错误：长度大于允许的最大值 \'{0}\'',
    'javax.faces.validator.LengthValidator.MINIMUM': '{1}：验证错误：长度小于允许的最小值 \'{0}\'',
    'javax.faces.validator.LongRangeValidator.MAXIMUM': '{1}：验证错误：值大于允许的最大值 \'{0}\'',
    'javax.faces.validator.LongRangeValidator.MINIMUM': '{1}：验证错误：值小于允许的最小值 \'{0}\'',
    'javax.faces.validator.LongRangeValidator.NOT_IN_RANGE': '{2}：验证错误：指定的属性不在预期值 {0} 和 {1} 之间。',
    'javax.faces.validator.LongRangeValidator.TYPE': '{0}：验证错误：值不是正确的类型。',
    'javax.faces.validator.NOT_IN_RANGE': '验证错误：指定的属性不在预期值 {0} 和 {1} 之间。',
    'javax.faces.validator.RegexValidator.PATTERN_NOT_SET': '必须设置 Regex 模式。',
    'javax.faces.validator.RegexValidator.PATTERN_NOT_SET_detail': 'Regex 模式必须设置为非空值。',
    'javax.faces.validator.RegexValidator.NOT_MATCHED': 'Regex 模式不匹配',
    'javax.faces.validator.RegexValidator.NOT_MATCHED_detail': '\'{0}\' 的 Regex 模式不匹配',
    'javax.faces.validator.RegexValidator.MATCH_EXCEPTION': '正则表达式中发生错误。',
    'javax.faces.validator.RegexValidator.MATCH_EXCEPTION_detail': '正则表达式中发生错误，\'{0}\'',
    'javax.faces.validator.BeanValidator.MESSAGE': '{0}'
}

const messages_zh_TW = {
    'javax.faces.component.UIInput.CONVERSION': '{0}：出現轉換錯誤。',
    'javax.faces.component.UIInput.REQUIRED': '{0}：驗證錯誤：必須輸入值。',
    'javax.faces.component.UIInput.UPDATE': '{0}：處理提交的資訊時出錯。',
    'javax.faces.component.UISelectOne.INVALID': '{0}：驗證錯誤：值無效',
    'javax.faces.component.UISelectMany.INVALID': '{0}：驗證錯誤：值無效',
    'javax.faces.converter.BigDecimalConverter.DECIMAL': '{2}: \'{0}\' 必須是帶符號的十進位數字值。',
    'javax.faces.converter.BigDecimalConverter.DECIMAL_detail': '{2}: \'{0}\' 必須是由零個或多個數位構成的帶符號十進位數字值，可以後跟小數點和小數部分。示例: {1}',
    'javax.faces.converter.BigIntegerConverter.BIGINTEGER': '{2}: \'{0}\' 必須是由一個或多個數字構成的數值。',
    'javax.faces.converter.BigIntegerConverter.BIGINTEGER_detail': '{2}: \'{0}\' 必須是由一個或多個數字構成的數值。示例: {1}',
    'javax.faces.converter.BooleanConverter.BOOLEAN': '{1}: \'{0}\' 必須是 \'true\' 或 \'false\'。',
    'javax.faces.converter.BooleanConverter.BOOLEAN_detail': '{1}: \'{0}\' 必須是 \'true\' 或 \'false\'。非 \'true\' 值都將會被視為 \'false\'。',
    'javax.faces.converter.ByteConverter.BYTE': '{2}: \'{0}\' 必須是介於 -128 和 127 之間的數值。',
    'javax.faces.converter.ByteConverter.BYTE_detail': '{2}: \'{0}\' 必須是介於 -128 和 127 之間的數值。示例: {1}',
    'javax.faces.converter.CharacterConverter.CHARACTER': '{1}: \'{0}\' 必須是有效字元。',
    'javax.faces.converter.CharacterConverter.CHARACTER_detail': '{1}: \'{0}\' 必須是有效的 ASCII 字元。',
    'javax.faces.converter.DateTimeConverter.DATE': '{2}：無法將 \'{0}\' 理解為日期。',
    'javax.faces.converter.DateTimeConverter.DATE_detail': '{2}：無法將 \'{0}\' 理解為日期。示例: {1}',
    'javax.faces.converter.DateTimeConverter.TIME': '{2}：無法將 \'{0}\' 理解為時間。',
    'javax.faces.converter.DateTimeConverter.TIME_detail': '{2}：無法將 \'{0}\' 理解為時間。示例: {1}',
    'javax.faces.converter.DateTimeConverter.DATETIME': '{2}：無法將 \'{0}\' 理解為日期和時間。',
    'javax.faces.converter.DateTimeConverter.DATETIME_detail': '{2}：無法將 \'{0}\' 理解為日期和時間。示例: {1}',
    'javax.faces.converter.DateTimeConverter.PATTERN_TYPE': '{1}：必須指定 \'pattern\' 或 \'type\' 屬性才能轉換值 \'{0}\'。',
    'javax.faces.converter.DoubleConverter.DOUBLE': '{2}: \'{0}\' 必須是由一個或多個數字構成的數值。',
    'javax.faces.converter.DoubleConverter.DOUBLE_detail': '{2}: \'{0}\' 必須是介於 4.9E-324 和 1.7976931348623157E308 之間的數值。示例: {1}',
    'javax.faces.converter.EnumConverter.ENUM': '{2}: \'{0}\' 必須能夠轉換為枚舉值。',
    'javax.faces.converter.EnumConverter.ENUM_detail': '{2}: \'{0}\' 必須能夠從一個含有常數 \'{1}\' 的枚舉值轉換為另一個枚舉值。',
    'javax.faces.converter.EnumConverter.ENUM_NO_CLASS': '{1}: \'{0}\' 必須能夠從一個枚舉值轉換為另一個枚舉值，但未提供枚舉類。',
    'javax.faces.converter.EnumConverter.ENUM_NO_CLASS_detail': '{1}: \'{0}\' 必須能夠從枚舉值轉換為枚舉值，但未提供枚舉類。',
    'javax.faces.converter.FloatConverter.FLOAT': '{2}: \'{0}\' 必須是由一個或多個數字構成的數值。',
    'javax.faces.converter.FloatConverter.FLOAT_detail': '{2}: \'{0}\' 必須是介於 1.4E-45 和 3.4028235E38 之間的數值。示例: {1}',
    'javax.faces.converter.IntegerConverter.INTEGER': '{2}: \'{0}\' 必須是由一個或多個數字構成的數值。',
    'javax.faces.converter.IntegerConverter.INTEGER_detail': '{2}: \'{0}\' 必須是介於 -2147483648 和 2147483647 之間的數值。示例: {1}',
    'javax.faces.converter.LongConverter.LONG': '{2}: \'{0}\' 必須是由一個或多個數字構成的數值。',
    'javax.faces.converter.LongConverter.LONG_detail': '{2}: \'{0}\' 必須是介於 -9223372036854775808 和 9223372036854775807 之間的數值。示例: {1}',
    'javax.faces.converter.NumberConverter.CURRENCY': '{2}：無法將 \'{0}\' 理解為貨幣值。',
    'javax.faces.converter.NumberConverter.CURRENCY_detail': '{2}：無法將 \'{0}\' 理解為貨幣值。示例: {1}',
    'javax.faces.converter.NumberConverter.PERCENT': '{2}：無法將 \'{0}\' 理解為百分數。',
    'javax.faces.converter.NumberConverter.PERCENT_detail': '{2}：無法將 \'{0}\' 理解為百分數。示例: {1}',
    'javax.faces.converter.NumberConverter.NUMBER': '{2}: \'{0}\' 不是數值。',
    'javax.faces.converter.NumberConverter.NUMBER_detail': '{2}: \'{0}\' 不是數值。示例: {1}',
    'javax.faces.converter.NumberConverter.PATTERN': '{2}: \'{0}\' 不是數值模式。',
    'javax.faces.converter.NumberConverter.PATTERN_detail': '{2}: \'{0}\' 不是數值模式。示例: {1}',
    'javax.faces.converter.ShortConverter.SHORT': '{2}: \'{0}\' 必須是由一個或多個數字構成的數值。',
    'javax.faces.converter.ShortConverter.SHORT_detail': '{2}: \'{0}\' 必須是介於 -32768 和 32767 之間的數值。示例: {1}',
    'javax.faces.converter.STRING': '{1}：無法將 \'{0}\' 轉換為字串。',
    'javax.faces.validator.DoubleRangeValidator.MAXIMUM': '{1}：驗證錯誤：值大於允許的最大值 \'{0}\'',
    'javax.faces.validator.DoubleRangeValidator.MINIMUM': '{1}：驗證錯誤：值小於允許的最小值 \'{0}\'',
    'javax.faces.validator.DoubleRangeValidator.NOT_IN_RANGE': '{2}：驗證錯誤：指定的屬性不在預期值 {0} 和 {1} 之間。',
    'javax.faces.validator.DoubleRangeValidator.TYPE': '{0}：驗證錯誤：值不是正確的類型',
    'javax.faces.validator.LengthValidator.MAXIMUM': '{1}：驗證錯誤：長度大於允許的最大值 \'{0}\'',
    'javax.faces.validator.LengthValidator.MINIMUM': '{1}：驗證錯誤：長度小於允許的最小值 \'{0}\'',
    'javax.faces.validator.LongRangeValidator.MAXIMUM': '{1}：驗證錯誤：值大於允許的最大值 \'{0}\'',
    'javax.faces.validator.LongRangeValidator.MINIMUM': '{1}：驗證錯誤：值小於允許的最小值 \'{0}\'',
    'javax.faces.validator.LongRangeValidator.NOT_IN_RANGE': '{2}：驗證錯誤：指定的屬性不在預期值 {0} 和 {1} 之間。',
    'javax.faces.validator.LongRangeValidator.TYPE': '{0}：驗證錯誤：值不是正確的類型。',
    'javax.faces.validator.NOT_IN_RANGE': '驗證錯誤：指定的屬性不在預期值 {0} 和 {1} 之間。',
    'javax.faces.validator.RegexValidator.PATTERN_NOT_SET': '必須設置 Regex 模式。',
    'javax.faces.validator.RegexValidator.PATTERN_NOT_SET_detail': 'Regex 模式必須設置為非空值。',
    'javax.faces.validator.RegexValidator.NOT_MATCHED': 'Regex 模式不匹配',
    'javax.faces.validator.RegexValidator.NOT_MATCHED_detail': '\'{0}\' 的 Regex 模式不匹配',
    'javax.faces.validator.RegexValidator.MATCH_EXCEPTION': '規則運算式中發生錯誤。',
    'javax.faces.validator.RegexValidator.MATCH_EXCEPTION_detail': '規則運算式中發生錯誤，\'{0}\'',
    'javax.faces.validator.BeanValidator.MESSAGE': '{0}'
}