$(document).scroll(function () {
    $(".ui-input-overlay,.ui-overlaypanel").hide()
})


function start() {
    PF('statusDialog').show();
}

function stop() {
    PF('statusDialog').hide();
}