import React, { Component } from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

class FileUploader extends Component {
    onDrop = (acceptedFiles) => {
        const formData = new FormData();
        formData.append('file', acceptedFiles[0]);

        axios
            .post('http://localhost:8080/upload', formData)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    render() {
        return (
            <div className="text-center mt-5">
                <Dropzone onDrop={this.onDrop}>
                    {({ getRootProps, getInputProps, isDragActive }) => (
                        <div {...getRootProps()}>
                            <input {...getInputProps()} />
                            {isDragActive
                                ? "Drop it like it's hot!"
                                : 'Click me or drag a file to upload!'}
                        </div>
                    )}
                </Dropzone>
                <p>Zu ergänzen: <br/>
                    Kundennummer, Kundenname und inkl. Pfad+Dateiname, wird ein JSON generiert und in die DB gespeichert <br/>
                    Forumlarfelder [   alskdjf     ]
                </p>
            </div>
        );
    }
}

export default FileUploader;