<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Web Lab 2</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="css/graph.css" rel="stylesheet">
    <link href="css/input.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
</head>
<body>
<header class="author">
    <text>Rusile | P32081 | 3281</text>
</header>

<main>

<p id="coord"></p>
    <div class="graph">
        <svg width="300" height="300" id="svg">

            <line x1="0" x2="300" y1="150" y2="150"></line>
            <line x1="150" x2="150" y1="0" y2="300"></line>

            <polygon style="opacity: 0.8; fill: black" points="150,0 145,15 155,15"></polygon>
            <polygon style="opacity: 0.8; fill: black" points="300,150 285,145 285,155"></polygon>

            <polygon points="150,150 150,100 250,100 250,150"></polygon>

            <polygon points="100,150 150,150 150,50"></polygon>

            <path d="M100,150 A80,80 90 0,0 150,200 L 150,150 Z"></path>

            <text x="285" y="135">X</text>
            <text x="160" y="15">Y</text>

            <line x1="50" x2="50" y1="140" y2="160"></line>
            <line x1="100" x2="100" y1="140" y2="160"></line>
            <line x1="200" x2="200" y1="140" y2="160"></line>
            <line x1="250" x2="250" y1="140" y2="160"></line>

            <line x1="140" x2="160" y1="50" y2="50"></line>
            <line x1="140" x2="160" y1="100" y2="100"></line>
            <line x1="140" x2="160" y1="200" y2="200"></line>
            <line x1="140" x2="160" y1="250" y2="250"></line>

            <text x="40" y="130" id="-r">-R</text>
            <text x="85" y="130" id="-r2">-R/2</text>
            <text x="190" y="130" id="r2">R/2</text>
            <text x="245" y="130" id="r">R</text>

            <text x="170" y="52.5" id="r">R</text>
            <text x="170" y="102.5" id="r2">R/2</text>
            <text x="170" y="202.5" id="-r2">-R/2</text>
            <text x="170" y="252.5" id="-r">-R</text>
        </svg>
    </div>

    <div class="input_wrapper">
        <form id="form" name="form" onchange="makeDot()">

            <div class="x_wrapper">
                <label>Choose X:</label>
                <input type="checkbox" id="-x3" name="x" value="-3">
                <label for="-x3">-3</label>
                <input type="checkbox" id="-x2" name="x" value="-2">
                <label for="-x2">-2</label>
                <input type="checkbox" id="-x" name="x" value="-1">
                <label for="-x">-1</label>
                <input type="checkbox" id="x0" name="x" value="0">
                <label for="x0">0</label>
                <input type="checkbox" id="x" name="x" value="1">
                <label for="x">1</label>
                <input type="checkbox" id="x2" name="x" value="2">
                <label for="x2">2</label>
                <input type="checkbox" id="x3" name="x" value="3">
                <label for="x3">3</label>
                <input type="checkbox" id="x4" name="x" value="4">
                <label for="x4">4</label>
                <input type="checkbox" id="x5" name="x" value="5">
                <label for="x5">5</label>
            </div>

            <div class="y_wrapper">
                <label for="y_value">Choose Y:</label>
                <input type="text" id="y_value" name="y_value" onchange="validateY()">
            </div>

            <div class="r_wrapper">
                <label>Choose R:</label>
                <input type="checkbox" id="r1" name="r" value="1">
                <label for="r1">1</label>
                <input type="checkbox" id="r1.5" name="r" value="1.5">
                <label for="r1.5">1.5</label>
                <input type="checkbox" id="r2" name="r" value="2">
                <label for="r2">2</label>
                <input type="checkbox" id="r2.5" name="r" value="2.5">
                <label for="r2.5">2.5</label>
                <input type="checkbox" id="r3" name="r" value="3">
                <label for="r3">3</label>
            </div>


            <div class="buttons">
                <div class="reset_button">
                    <input id="reset" type="reset" value="Reset" onclick="resetInput()">
                </div>
                <div class="check_button">
                    <input name="submit_button" id="submit" type="submit" value="Check">
                </div>
            </div>
            <div class="errors">
            <p style="color: red; font-size: 0.7em" id="x_error"></p>
            <p style="color: red; font-size: 0.7em" id="y_error"></p>
            <p style="color: red; font-size: 0.7em" id="r_error"></p>
            </div>
        </form>
    </div>

    <div class="table_wrapper" style="height: 400px; overflow-x: hidden;
         overflow-y: auto; display: block">

        <table id="res_table">
            <thead>
            <tr>
                <th scope="col">Time</th>
                <th scope="col">Script time</th>
                <th scope="col">X</th>
                <th scope="col">Y</th>
                <th scope="col">R</th>
                <th scope="col">Result</th>
            </tr>
            </thead>

            <tbody>

            </tbody>

        </table>
    </div>

</main>
<script src="js/validator.js"></script>
<script src="js/requestSender.js"></script>
<script src="js/tableController.js"></script>
<script src="js/dotController.js"></script>
<script src="js/rChanger.js"></script>
<script src="js/checkboxController.js"></script>
</body>
</html>