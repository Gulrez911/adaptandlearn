$('.login-form input').on('focus', function () {
    $(this).closest('div').find('label').addClass('active');
}).on('focusout', function () {
    var value = $.trim($(this).val());
    if (value.length === 0) {
        $(this).closest('div').find('label').removeClass('active');
    }
});
var searchInteraction = false, notificationInteraction = false, userInfo = false;

var init = {
    defaultFunc: function () {
        if (searchInteraction) {
            $('header nav ul li.search').addClass('active');
        } else {
            $('header nav ul li.search').removeClass('active');
        }
        if (notificationInteraction) {
            $('header nav ul li.notification').addClass('active');
        } else {
            $('header nav ul li.notification').removeClass('active');
        }
        if (userInfo) {
            $('header nav ul li.user-info').addClass('active');
        } else {
            $('header nav ul li.user-info').removeClass('active');
        }
    }
}

init.defaultFunc();

$('header nav ul li.search a').on('click', function () {
    searchInteraction = !searchInteraction;
    init.defaultFunc();
});
$('header nav ul li.notification>a').on('click', function () {
    notificationInteraction = !notificationInteraction;
    init.defaultFunc();
});
$('header nav ul li.user-info .thumbnail').on('click', function () {
    userInfo = !userInfo;
    init.defaultFunc();
});
$('header nav ul li .drop-menu-bg').on('click', function () {
    searchInteraction = false;
    notificationInteraction = false;
    userInfo = false;
    init.defaultFunc();
});
