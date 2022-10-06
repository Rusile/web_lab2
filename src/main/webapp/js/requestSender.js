$('form').on('submit', function (e) {
    e.preventDefault();

    if (validateAllValues()) {
        send(controller.xValue, controller.yValue, controller.rValue);
    }
    return false;
})


function send(x, y, r) {
    console.log("Sending request");
    document.getElementById('x_error').innerHTML = "";
    document.getElementById('y_error').innerHTML = "";
    document.getElementById('r_error').innerHTML = "";
    $.ajax({
        type: "POST",
        url: "./controller",
        dataType: "html",
        data: "x=" + x +
            "&y=" + y +
            "&r=" + r,
        beforeSend: function () {
            $('submit').disabled = true;
        },
        error: function (xhr, status, error) {
            let errorMessage = xhr.status + ': ' + xhr.statusText
            alert('Error - ' + errorMessage);
        },
        success: function (data) {
            $('submit').disabled = false;
            addData(data);
        },
    });
}

