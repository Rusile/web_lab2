class InputController {
    xValue = $('.x_wrapper > input:checkbox:checked').val();
    yValue = document.getElementById("y_value").value;
    rValue = $('.r_wrapper > input:checkbox:checked').val();
}

let controller;

function validateAllValues() {
    controller = new InputController();
    let xV = controller.xValue;
    let yV = controller.yValue;
    let rV = controller.rValue;
    if (isNaN(parseInt(xV)) || !(-3 <= parseInt(xV) && parseInt(xV) <= 5)) {
        $("#x_error").html("Please select an integer in the interval [-3;5] as the parameter X!");
        return false;
    } else {
        document.getElementById('x_error').innerHTML = "";
    }
    if (isNaN(parseFloat(yV)) || !(-5 <= parseFloat(yV) && parseFloat(yV) <= 3)) {
        $("#y_error").html("Please enter a float number in the interval [-5;3] as the Y parameter!");
        return false;
    } else {
        document.getElementById('y_error').innerHTML = "";
    }
    if (isNaN(parseInt(rV)) || !(1 <= parseInt(rV) && parseInt(rV) <= 3)) {
        $("#r_error").html("Please select an integer in the interval [1;3] as the parameter R!");
        return false;
    } else {
        document.getElementById('r_error').innerHTML = "";
    }
    return true;

}

function validateR() {
    controller = new InputController();
    let rV = controller.rValue;
    if (isNaN(parseInt(rV)) || !(1 <= parseInt(rV) && parseInt(rV) <= 3)) {
        $("#r_error").html("Please select an integer in the interval [1;3] as the parameter R!");
        return false;
    } else {
        document.getElementById('r_error').innerHTML = "";
    }
    return true;
}

$('.graph').click(function(e) {
    let isCorrect = validateR();
    if (!isCorrect)
        return;

    let rVal = controller.rValue;
    let target = this.getBoundingClientRect();
    let x = Math.round(e.clientX - target.left);
    let y = Math.round(e.clientY - target.top);
    let xVal = Math.round((x - 150) / (100 / rVal));
    let yVal = (y - 150) / (-100 / rVal).toFixed(3);
    send(xVal, yVal, rVal);
});

function validateY() {
    let yValue = document.getElementById("y_value");
    yValue.value = yValue.value.replace(',', '.').replace(' ', '');
    if (isNaN(parseFloat(yValue.value)) || !(/^([-+]?\d*\.?\d+)(?:[e]([-+]?\d+))?$/.test(yValue.value)))
        yValue.value = '';
}