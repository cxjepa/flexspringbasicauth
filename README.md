# flexspringbasicauth
Skeleton project setting up a REST spring mvc web application using spring security and Flex as client. 

Following the tutorial from http://www.codesandnotes.be/2015/07/24/angularjs-web-apps-for-spring-based-rest-services-security-the-server-side-part-2-csrf/ I provide a solution to remove completely Blazeds from your dependencies, so to be able to switch to newer version of java libraries and tools (JDK1.8, Tomcat 8) saving the client side exisiting code (with adjustments of course).

The idea is to use Javascript/JQuery just for communication retrieving the responses using the actionscript class ExternalInterface.  

The following 

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

can be easily adapted to be a more general purpose passing as parameters the type and url.
