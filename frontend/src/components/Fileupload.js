import React from 'react';
import { useDropzone } from 'react-dropzone';

const FileUploader = () => {
    const onDrop = (acceptedFiles) => {
        const formData = new FormData();
        acceptedFiles.forEach((file) => {
            formData.append('files', file);
        });

        fetch('/upload', {
            method: 'POST',
            body: formData,
        })
            .then((response) => {
                // Handle response
            })
            .catch((error) => {
                // Handle error
            });
    };

    const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {isDragActive ? (
                <p>Drop the files here...</p>
            ) : (
                <p>Drag and drop files here, or click to select files</p>
            )}
        </div>
    );
};

export default FileUploader;


// BACKUP OLD CODE
/*
import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'


function FileUploader() {
    const onDrop = useCallback(acceptedFiles => {
        // Do something with the files
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({
        onDrop,
        accept: '.xls, .xlsx' // Nur XLS- und XLSX-Dateien akzeptieren
    })

    return (
        <div>
            <div>BOOOOOOOOM1</div>

            <div {...getRootProps()}>
                <input {...getInputProps()} />
                {
                    isDragActive ?
                        <p>Drop the files here ...</p> :
                        <p>Drag 'n' drop some files here, or click to select files</p>
                }
            </div>
        </div>
    )
}

export default FileUploader
 */
