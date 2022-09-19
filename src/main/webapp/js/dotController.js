function makeDot() {
    let oldDot = document.getElementById("dot_c");
    if (isNaN(oldDot))
        oldDot.remove();

    let xValue = $('.x_wrapper > input:checkbox:checked').val();
    let yValue = document.getElementById("y_value").value;
    let rValue = $('.r_wrapper > input:checkbox:checked').val();

    if (isNaN(rValue))
        return;

    switchR(rValue);

    if (isNaN(xValue) || isNaN(yValue))
        return;

    let rV = rValue;
    let xV = 150 + xValue * (100 / rV);
    let yV = 150 - yValue * (100 / rV);

    if ((xV > 300 || xV < 0) || (yV > 300 || yV < 0))
        return;

    let cont = document.getElementById("svg");
    let svgns = "http://www.w3.org/2000/svg";

    let circle = document.createElementNS(svgns, 'circle');
    circle.id = "dot_c";
    circle.setAttributeNS(null, 'cx', xV);
    circle.setAttributeNS(null, 'cy', yV);
    circle.setAttributeNS(null, 'r', 5);
    circle.setAttributeNS(null, 'style', 'fill: #94a2ad;  stroke-width: 1px;');
    cont.appendChild(circle);
}

