var client = filestack.init("A9oLRH0uOQx236XVvgRSYz");

document.getElementById("uploadBtn").addEventListener("click", function (ev) {
    client.pick({
        accept: 'image/*',
        maxFiles: 1
    }).then(function (result) {
        var newResult = JSON.parse(result);
        var picName = newResult.filesUploaded.filename;
        var picURL = newResult.filesUploaded.url;
        console.log(picURL);
        console.log(picName);
        document.getElementById("picName").innerText = picName;
        document.getElementById("picUrlInput").value = picURL;
    })
});

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