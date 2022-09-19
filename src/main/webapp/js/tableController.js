const table = document.querySelector('#res_table > tbody');
const storage = window.localStorage;
if (storage.getItem('tableData') != null) {
    table.innerHTML += storage.getItem('tableData');
}

function addData(data) {
    table.innerHTML = parseJSON(data);

}

function resetInput() {
    let tBody = document.querySelector('#res_table > tbody');
    document.getElementById('x_error').innerHTML = "";
    document.getElementById('y_error').innerHTML = "";
    document.getElementById('r_error').innerHTML = "";
    $.ajax({
        type: "POST",
        url: "./check-values",
        dataType: "html",
        data: "clearHistory=true",
        beforeSend: function () {
            $('reset').disabled = true;
        },
        success: function () {
            $('reset').disabled = false;
            tBody.innerHTML = '';
        },
    });
}

function parseJSON(data) {
    let json = JSON.parse(data);
    let historyArr = json.history;
    let result = "";
    for (let i = 0; i < historyArr.length; i++) {
        result += "<tr>" +
            "<td>" + historyArr[i].currentTime + "</td>" +
            "<td>" + historyArr[i].scriptTime + "</td>" +
            "<td>" + historyArr[i].coordinates.x + "</td>" +
            "<td>" + historyArr[i].coordinates.y  + "</td>" +
            "<td>" + historyArr[i].coordinates.r  + "</td>" +
            "<td>" + historyArr[i].hit + "</td>" +
            "</tr>";
    }
    return result;
}