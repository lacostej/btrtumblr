<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
      <script type="text/javascript">
        var uservoiceOptions = {
          /* required */
          key: 'btrtumblr',
          host: 'btrtumblr.uservoice.com',
          forum: '40642',
          showTab: true,
          /* optional */
          alignment: 'left',
          background_color:'#f00',
          text_color: 'white',
          hover_color: '#06C',
          lang: 'en'
        };

        function _loadUserVoice() {
          var s = document.createElement('script');
          s.setAttribute('type', 'text/javascript');
          s.setAttribute('src', ("https:" == document.location.protocol ? "https://" : "http://") + "cdn.uservoice.com/javascripts/widgets/tab.js");
          document.getElementsByTagName('head')[0].appendChild(s);
        }
        _loadSuper = window.onload;
        window.onload = (typeof window.onload != 'function') ? _loadUserVoice : function() { _loadSuper(); _loadUserVoice(); };
    </script>
      
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <!--div id="grailsLogo" class="logo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div-->
        <g:layoutBody />
    </body>
</html>