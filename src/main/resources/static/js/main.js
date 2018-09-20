// ========== FILESTACK JS ========== \\
// |                                | \\

var client = filestack.init("A9oLRH0uOQx236XVvgRSYz");

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
