var numPoints = 500,
    size = 400,
    numRows = 50,
    numCols = 32,
    data = null,
    cells = null,
    color = d3.interpolateRgb("#fff", "#c09"),
    datafromfile = null,
    fieldwidth = 52483,
    fieldheight = 67920,
    cellwidth = fieldwidth/numCols,
    cellheight = fieldheight/numRows,
    XMLHttpRequestObject = false,
    filename = null,
    timer = null,
    streamdata = null,
    streamvalue = null;
 
      if (window.XMLHttpRequest) {
        XMLHttpRequestObject = new XMLHttpRequest();
      } else if (window.ActiveXObject) {
        XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
      }
      
var getEmptyCells = function() {
    var emptyCells = [];
    for (var rowNum = 0; rowNum < numRows; rowNum++) {
        emptyCells.push([]);
        var row = emptyCells[emptyCells.length - 1];
        for (var colNum = 0; colNum < numCols; colNum++) {
            row.push({
                row: rowNum,
                col: colNum,
                density: 0,
                points: []
            });
        }
    }
    return emptyCells;
};

var clearCells = function() {
    for (var rowNum = 0; rowNum < numRows; rowNum++) {
        for (var colNum = 0; colNum < numCols; colNum++) {
            cells[rowNum][colNum].density = 0;
            cells[rowNum][colNum].points = [];
        }
    }
};

var UpdateData = function() {
    data = [];

    if (cells === null) {
        cells = getEmptyCells();
    }
    else {
        clearCells();
    }
    if (filename != null)
		ReadFiles();
		
	if (datafromfile != null){	
		datafromfile = datafromfile.split('{').join('');
		datafromfile = datafromfile.split(' ').join('');
		streamdata = datafromfile.split("}");
		
	    var x, y, col, row;
	    for (var i = 0; i < streamdata.length; i++) {
	    	streamvalue = streamdata[i].split(",");
	    	var cell_x1, cell_x2, cell_y1, cell_y2, ts, timeInGame;
	    	for (var j = 0; j < streamvalue.length; j++) {
		        
		        if (streamvalue[j].indexOf('cell_x1') == 0) {
		            var start  = streamvalue[j].indexOf('=') + 1;
		            var length = streamvalue[j].length - start;
		            cell_x1 = streamvalue[j].substr(start, length);
		            cell_x1 = parseFloat(cell_x1) + 50;
		        }
		        else if (streamvalue[j].indexOf('cell_x2') == 0) {
		            var start  = streamvalue[j].indexOf('=') + 1;
		            var length = streamvalue[j].length - start;
		            cell_x2 = streamvalue[j].substr(start, length);
		            cell_x2 = parseFloat(cell_x2) + 50;
		        }
		        else if (streamvalue[j].indexOf('cell_y1') == 0) {
		            var start  = streamvalue[j].indexOf('=') + 1;
		            var length = streamvalue[j].length - start;
		            cell_y1 = streamvalue[j].substr(start, length);
		            cell_y1 = parseFloat(cell_y1) + 33965;
		        }
		        else if (streamvalue[j].indexOf('cell_y2') == 0) {
		            var start  = streamvalue[j].indexOf('=') + 1;
		            var length = streamvalue[j].length - start;
		            cell_y2 = streamvalue[j].substr(start, length);
		            cell_y2 = parseFloat(cell_y2) + 33965;
		        }
		        else if (streamvalue[j].indexOf('ts') == 0) {
		            var start  = streamvalue[j].indexOf('=') + 1;
		            var length = streamvalue[j].length - start;
		            ts = streamvalue[j].substr(start, length);
		        }
		        else if (streamvalue[j].indexOf('timeInGame') == 0) {
		            var start  = streamvalue[j].indexOf('=') + 1;
		            var length = streamvalue[j].length - start;
		            timeInGame = streamvalue[j].substr(start, length);
		        }
		    }
	    	
	        x = ((parseFloat(cell_x1) + parseFloat(cell_x2))/2);
	        y = ((parseFloat(cell_y1) + parseFloat(cell_y2))/2);
	        col = Math.min(Math.floor(x / cellwidth), numCols - 1);
	        row = Math.min(Math.floor(y / cellheight), numRows - 1);
	
	        data.push({
	            x: x,
	            y: y,
	            col: col,
	            row: row,
	            cell: cells[row][col],
	            ind: i
	        });
	
	        cells[row][col].points.push(data[data.length - 1]);
	        document.getElementById('heatchartdata').innerHTML = 'Time in game: ' + timeInGame + '<br>TimeStamp: ' + ts + '<br>x,y = ' + x + ', ' + y + '<br>[row][col] = [' + row + '][' + col + ']'; 
	    }
	}
    updateHeatchart();
};

var selectPoints = function(points) {
    d3.selectAll(points).attr("r", 4).attr("stroke", "#f00").attr("stroke-width", 3);

    for (var i = 0; i < points.length; i++) {
        points[i].parentNode.appendChild(points[i]);
    }
};

var deselectPoints = function(points) {
    d3.selectAll(points).attr("r", 2).attr("stroke", "none");
};

var selectCell = function(cell) {
    d3.select(cell).attr("stroke", "#f00").attr("stroke-width", 3);

    cell.parentNode.parentNode.appendChild(cell.parentNode);
    cell.parentNode.appendChild(cell);
};

var deselectCell = function(cell) {
    d3.select(cell).attr("stroke", "#fff").attr("stroke-width", 1);
};

var onPointOver = function(point, data) {
    selectPoints([point]);
    var cell = d3.select('div#heatchart').select('[cell="r' + data.row + 'c' + data.col + '"]');
    selectCell(cell.node());
};

var onPointOut = function(point, data) {
    deselectPoints([point]);
    var cell = d3.select('div#heatchart').select('[cell="r' + data.row + 'c' + data.col + '"]');
    deselectCell(cell.node());
};

var onCellOver = function(cell, data) {
    selectCell(cell);
};

var onCellOut = function(cell, data) {
    deselectCell(cell);
};

var createHeatchart = function() {
    var min = 999;
    var max = -999;
    var l;

    for (var rowNum = 0; rowNum < cells.length; rowNum++) {
        for (var colNum = 0; colNum < numCols; colNum++) {
            l = cells[rowNum][colNum].points.length;

            if (l > max) {
                max = l;
            }
            if (l < min) {
                min = l;
            }
        }
    }

    var heatchart = d3.select('div#heatchart').append("svg:svg").attr("width", size).attr("height", size);

    heatchart.selectAll("g").data(cells).enter().append("svg:g").selectAll("rect").data(function(d) {
        return d;
    }).enter().append("svg:rect").attr("x", function(d, i) {
        return d.col * (size / numCols);
    }).attr("y", function(d, i) {
        return d.row * (size / numRows);
    }).attr("width", size / numCols).attr("height", size / numRows).attr("fill", function(d, i) {
        return color((d.points.length - min) / (max - min));
    }).attr("stroke", "#fff").attr("cell", function(d) {
        return "r" + d.row + "c" + d.col;
    }).on("mouseover", function(d) {
        onCellOver(this, d);
    }).on("mouseout", function(d) {
        onCellOut(this, d);
    });
};

var updateHeatchart = function() {
    var min = 999;
    var max = -999;
    var l;

    for (var rowNum = 0; rowNum < cells.length; rowNum++) {
        for (var colNum = 0; colNum < numCols; colNum++) {
            l = cells[rowNum][colNum].points.length;

            if (l > max) {
                max = l;
            }
            if (l < min) {
                min = l;
            }
        }
    }

    d3.select('div#heatchart').select("svg").selectAll("g").data(cells).selectAll("rect").data(function(d) {
        return d;
    }).attr("x", function(d, i) {
        return d.col * (size / numCols);
    }).attr("y", function(d, i) {
        return d.row * (size / numRows);
    }).attr("fill", function(d, i) {
        return color((d.points.length - min) / (max - min));
    }).attr("cell", function(d) {
        return "r" + d.row + "c" + d.col;
    }).on("mouseover", function(d) {
        onCellOver(this, d);
    }).on("mouseout", function(d) {
        onCellOut(this, d);
    });
};

var ReadFiles = function(){
   if(XMLHttpRequestObject) {
   			var datafromfiletemp = datafromfile;
   			var timeout = 10;
   			while((datafromfiletemp == datafromfile || datafromfile == null) && timeout >=1){
   				timeout = timeout - 1;
	   			XMLHttpRequestObject.overrideMimeType("text/html");
	          XMLHttpRequestObject.open("GET", filename, false); 
	 
	          XMLHttpRequestObject.onreadystatechange = function() 
	          { 
	            if (XMLHttpRequestObject.readyState == 4 && 
	              (XMLHttpRequestObject.status == 200|| XMLHttpRequestObject.status == 0)) { 
	                datafromfile = XMLHttpRequestObject.responseText; 
	            } 
	          }
	       } 
          XMLHttpRequestObject.send(null); 
    }
};
var onStartUpdate = function() {
    timer = setInterval(function(){UpdateData()}, 1000);
};
 
var init = function() {
	filename = 'output_10000.txt';
    UpdateData();
    createHeatchart();
};
var onWindowChange = function(event) {
    WindowTime = event.target.options[event.target.selectedIndex].value;
    filename = 'output_' + WindowTime + '.txt';
    UpdateData()();
};
var onStopUpdate = function(){
	clearInterval(timer);
};
init();