<html>
<head>
	<title>Traffic Tickets</title>
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $.ajax({
                    url: '/maven.wink.sample/rest/tickets',
                    async: true,
                    cache: false,
                    dataType: 'json',
                    type: 'GET',
                    success: function(data){
                        for (var i=0;i<data.length;i++){
                            $('#tickets').text(data[i].name + ".." + data[i].location);
                        }
                    }
                })
            });
        </script>
</head>


<body>
<h2>Traffic Tickets</h2><hr>
<div id="tickets">Loading tickets....</div>
</body>
</html>
