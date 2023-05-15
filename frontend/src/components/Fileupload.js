import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'

function FileUploader() {
    const onDrop = useCallback(acceptedFiles => {
        // Do something with the files
        const file = acceptedFiles[0];
        // Create a FormData object to send the file to the server
        const formData = new FormData();
        formData.append('file', file);
        // Send the file using fetch or axios
        fetch('/upload', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                // Get the path from the data
                const path = data.path;
                // Do something with the path
                console.log(path);
            })
            .catch(error => {
                // Handle error
                console.error(error);
            });
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({
        onDrop,
        accept: '.xls, .xlsx' // Nur XLS- und XLSX-Dateien akzeptieren
    })

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p>Drop the file here ...</p> :
                    <p>Drag 'n' drop some file here, or click to select files</p>
            }
        </div>
    )
}
export default FileUploader
