import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

class ReferenceData extends Component {
    onDrop = (acceptedFiles) => {
        const formData = new FormData();
        formData.append('file', acceptedFiles[0]);

        axios
            .post('http://localhost:8080/masterdata', formData)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    render() {
        return (
            <div className="mainstyle">
                <div className="divcontent">
                    <Dropzone onDrop={this.onDrop}>
                        {({getRootProps, getInputProps, isDragActive}) => (
                            <div {...getRootProps()} className="dropzonestyle rounded border">
                                <input {...getInputProps()} />
                                {isDragActive
                                    ? "Drop it like it's hot!"
                                    : 'Click me or drag a file to upload!'}
                            </div>
                        )}
                    </Dropzone>
                    <div className="divcontent rounded border">
                        <p>Hier soll das Stammdaten Excelfile hochgeladen werden<br/>
                            Automatisierter Prozess zur Aktualisierung der hinterlegten Datenbank
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}

export default ReferenceData;