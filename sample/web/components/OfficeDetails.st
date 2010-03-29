<html>
<head>
    <title>$title$</title>

    <script type='text/javascript' src='http://www.google.com/jsapi'></script>
    <script type='text/javascript'>
        google.load('visualization', '1', {'packages': ['geomap']});
        google.setOnLoadCallback(drawMap);

        function drawMap() {
            var data = new google.visualization.DataTable();
            data.addRows($length(offices)$);
            data.addColumn('string', 'Country');
            data.addColumn('number', 'Consultants');

            $offices:{office |
                data.setValue($i0$, 0, '$office.region$');
                data.setValue($i0$, 1, $office.consultants$);
            }$

            var options = {};
            options['dataMode'] = 'regions';

            var container = document.getElementById('map_canvas');
            var geomap = new google.visualization.GeoMap(container);
            geomap.draw(data, options);
        }
        ;
    </script>
</head>
<body>

<h1>Office details</h1>

<table>
    <tr>
        <th>Region</th>
        <th>Consultants</th>
    </tr>
    $offices:{office |
    <tr>
        <td>$office.region$</td>
        <td>$office.consultants$</td>
    </tr>
    }$
</table>

<p>Sample page based on a copy and paste from the google visualisation gallery</p>

<div id='map_canvas'></div>

</body>
</html>
