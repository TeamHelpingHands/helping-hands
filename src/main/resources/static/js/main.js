// ========== FILESTACK JS ========== \\
// |                                | \\
// ================================== \\

var client = filestack.init("AYR6oC4gPTbSY1SMpRUnCz");

document.getElementById("uploadBtn").addEventListener("click", function (ev) {
    client.pick({
        accept: 'image/*',
        maxFiles: 1,
        imageDim: [500, 500],
        modalSize: [750, 500],
        fromSources: String[
            "localFileSystem",
                "url",
                "imagesearch",
                "facebook",
                "instagram",
                "googledrive",
                "dropbox",
                "webcam"
            ],
        transformations: {
            circle: false,
            crop: {
                aspectRatio: 1,
                force: true
            },
            rotate: true
        }
    }).then(function (result) {
        var picName = result.filesUploaded[0].filename;
        var picURL = result.filesUploaded[0].url;
        document.getElementById("picName").innerText = picName;
        document.getElementById("picUrlInput").value = picURL;
    })
});

// ===== The Returned JSON File from `Filestack` ===== \\

// {
//     "filesUploaded": [{
//         "filename":"hh-logo.png",
//         "handle":"vYi9HHYYREecO1vAYNuj",
//         "mimetype":"image/png",
//         "originalPath":"hh-logo.png",
//         "size":36042,
//         "source":"local_file_system",
//         "url":"https://cdn.filestackcontent.com/vYi9HHYYREecO1vAYNuj",
//         "uploadId":"a267af2c136e7f3292413225fdacbc54d",
//         "originalFile":{
//           "name":"hh-logo.png",
//           "type":"image/png",
//           "size":36042},
//           "status":"InTransit"
//         }],
//     "filesFailed":[]}

// ================================== \\
// ================================== \\
// ================================== \\


// AmCharts \\

// var noEventsEnrolled = document.getElementById("eventsEnrolled").value;
// var noOfDidAttend = document.getElementById("noOfDidAttend").value;
//
// var chartData = [ {
//     "eventsEnrolled": noEventsEnrolled
// }, {
//     "eventsAttended": noOfDidAttend,
// }];
//
//
// AmCharts.makeChart("chartdiv", {
//     "theme": "none",
//     "type": "gauge",
//     "dataProvider": chartData,
//     "axes": [{
//         "topTextFontSize": 20,
//         "topTextYOffset": 70,
//         "axisColor": "#31d6ea",
//         "axisThickness": 1,
//         "endValue": 100,
//         "gridInside": true,
//         "inside": true,
//         "radius": "50%",
//         "valueInterval": 10,
//         "tickColor": "#67b7dc",
//         "startAngle": -90,
//         "endAngle": 90,
//         "unit": "%",
//         "bandOutlineAlpha": 0,
//         "bands": [{
//             "color": "#0080ff",
//             "endValue": 100,
//             "innerRadius": "105%",
//             "radius": "170%",
//             "gradientRatio": [0.5, 0, -0.5],
//             "startValue": 0
//         }, {
//             "color": "#3cd3a3",
//             "endValue": 0,
//             "innerRadius": "105%",
//             "radius": "170%",
//             "gradientRatio": [0.5, 0, -0.5],
//             "startValue": 0
//         }]
//     }],
//         "arrows": [{
//         "alpha": 1,
//         "innerRadius": "35%",
//         "nailRadius": 0,
//         "radius": "170%"
//     }]
// });
//
// // set random value
// function displaValue() {
//     var value = no;
//     chart.arrows[0].setValue(value);
//     chart.axes[0].setTopText(value + " %");
//     // adjust darker band to new value
//     chart.axes[0].bands[1].setEndValue(value);
// }


// ======================================= \\

