var jsReady = false;

function isReady() {
    return jsReady;
}

function pageInit() {
     jsReady = true;
}

function doLogin(user,password) {
	var cookie = JSON.parse($.cookie('licit'));
    var data = 'username=' + user + '&password=' + password;
    $.ajax({
        data: data,
        headers: {'X-CSRF-TOKEN': cookie.csrf},
        timeout: 1000,
        type: 'POST',
        url: '/licit/login'
    }).done(function (data, textStatus, jqXHR) {
        document.getElementById("ExternalInterfaceExample").doLogin(textStatus);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        document.getElementById("ExternalInterfaceExample").doLogin(textStatus);
    });
}

function doLogout() {
	var cookie = JSON.parse($.cookie('licit'));
    $.ajax({
        data: {},
        headers: {'X-CSRF-TOKEN': cookie.csrf},
        timeout: 1000,
        type: 'POST',
        url: '/licit/logout'
    }).done(function (data, textStatus, jqXHR) {
		document.getElementById("ExternalInterfaceExample").doLogout(textStatus);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        document.getElementById("ExternalInterfaceExample").doLogout(textStatus);
    });
}

function getUser() {
	$.ajax({
        type: 'GET',
        url: '/licit/user'
    }).done(function (data, textStatus, jqXHR) {
        var csrfToken = jqXHR.getResponseHeader('X-CSRF-TOKEN');
        if (csrfToken) {
            var cookie = JSON.parse($.cookie('licit'));
            cookie.csrf = csrfToken;
            $.cookie('licit', JSON.stringify(cookie));
        }
        document.getElementById("ExternalInterfaceExample").getUser(data);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 401) { // HTTP Status 401: Unauthorized
            var cookie = JSON.stringify({method: 'GET', url: '/licit/', csrf: jqXHR.getResponseHeader('X-CSRF-TOKEN')});
            $.cookie('licit', cookie);
            document.getElementById("ExternalInterfaceExample").getUser('401');
        } else {
            alert('Houston, we have a problem...');
        }
    });
}