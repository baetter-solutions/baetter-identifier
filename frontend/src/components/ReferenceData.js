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
                    <div className="divcontent rounded border">
                        <p>Stammdaten integrieren bzw. aktualisieren</p>
                    </div>
                    <div className="divcontent">
                    <Dropzone onDrop={this.onDrop}>
                        {({getRootProps, getInputProps, isDragActive}) => (
                            <div {...getRootProps()} className="dropzonestyle rounded border">
                                <input {...getInputProps()} />
                                {isDragActive
                                    ? "Drop it like it's hot!"
                                    : 'Klicken oder Datei mit der Maus darauf schieben!'}
                            </div>
                        )}
                    </Dropzone>
                    </div>
                </div>
            </div>
        );
    }
}

export default ReferenceData;