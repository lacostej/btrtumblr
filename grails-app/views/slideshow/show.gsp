<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>Slideshow</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="/slideshow/css/slideshow.css" media="screen" />
        <script type="text/javascript" src="/slideshow/js/mootools.js"></script>
        <script type="text/javascript" src="/slideshow/js/slideshow.js"></script>
        <script type="text/javascript">
        //<![CDATA[
          window.addEvent('domready', function(){
            var data = {
      <g:each in="${photos}">
              '${it.url}': {caption: '${it.caption} - ${it.hover}', thumbnail: '${it.thumbnail}'},
      </g:each>
            };
            var myShow = new Slideshow('show', data, {controller: true, height: ${maxHeight}, thumbnails: true, width: ${maxWidth}});
          });
        //]]>
        </script>
</head>
<body>
  
  <div id="show" class="slideshow">
    <g:if test="${photos.size() > 0}">
      <img src="${photos[0].url}" alt="${photos[0].caption}" />
    </g:if>
    <g:else>
      No pictures in slideshow...
    </g:else>
  </div>
</body>
</html>
