import React, { useEffect, useState } from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

const ReferenceData = () => {
    const [counters, setCounters] = useState({});

    useEffect(() => {
        fetch('/masterdataresponse')
            .then(response => response.json())
            .then(data => setCounters(data))
            .catch(error => console.error(error));
    }, []);

    const onDrop = acceptedFiles => {
        const formData = new FormData();
        formData.append('file', acceptedFiles[0]);

        axios
            .post('http://localhost:8080/masterdata', formData)
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    };

    return (
        <div className="mainstyle">
            <div className="divcontent">
                <div className="div2ndlvl rounded border">
                    <p>Hier kann man die Stammdaten importieren bzw. aktualisieren</p>
                </div>
                <div className="div2ndlvl">
                    <Dropzone onDrop={onDrop}>
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
                <div className="div2ndlvl rounded border">
                    Total transmitted: {counters.saveCounter}<br />
                    New: {counters.updateCounter}<br />
                    Updated: {counters.totalCount}
                </div>
            </div>
        </div>
    );
};

export default ReferenceData;
