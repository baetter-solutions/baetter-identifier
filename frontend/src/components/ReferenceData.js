import React, {useEffect, useState} from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

function CounterUpdater({setSavecount, setupdateCounter, settotalCount}) {
    useEffect(() => {
        axios
            .get('http://localhost:8080/products/totaltransmitted')
            .then(response => {
                setSavecount(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, [setSavecount]);

    useEffect(() => {
        axios
            .get('http://localhost:8080/products/countofnewitems')
            .then(response => {
                setupdateCounter(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, [setupdateCounter]);

    useEffect(() => {
        axios
            .get('http://localhost:8080/products/countoffupdated')
            .then(response => {
                settotalCount(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, [settotalCount]);

    return null;
}

const onDrop = acceptedFiles => {
    const formData = new FormData();
    formData.append('file', acceptedFiles[0]);

    axios
        .post('http://localhost:8080/masterdata', formData)
        .then(response => {
            console.log(response.data);
            window.location.reload();
        })
        .catch(error => {
            console.error(error);
        });
};

export default function ReferenceData() {
    const [dbcount, setDBcount] = useState();
    const [saveCounter, setSavecount] = useState();
    const [updateCounter, setupdateCounter] = useState();
    const [totalCount, settotalCount] = useState();

    useEffect(() => {
        axios
            .get('http://localhost:8080/products/count')
            .then(response => {
                setDBcount(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);


    return (
        <article className="mainstyle">
            <div className="divcontent">
                <div className="div2ndlvl rounded border">
                    Hier kann man die Stammdaten <br/>importieren bzw. aktualisieren
                </div>
                <div className="div2ndlvl">
                    <Dropzone onDrop={onDrop}>
                        {({getRootProps, getInputProps, isDragActive}) => (
                            <div {...getRootProps()}
                                 className="dropzonestyle rounded border shadow-sm  bg-body-tertiary">
                                <input {...getInputProps()} />
                                {isDragActive
                                    ? "Drop it like it's hot!"
                                    : 'Klicken oder Datei mit der Maus darauf schieben!'}
                            </div>
                        )}
                    </Dropzone>
                </div>
                <div className="div2ndlvl rounded border">
                    Total number of articles: {dbcount}
                </div>
                <div className="div2ndlvl rounded border">
                    <h3>Last Transmission</h3>
                    New: {updateCounter}<br/>
                    Updated: {totalCount}<br/>
                    Total transmitted: {saveCounter}
                </div>

            </div>
            <CounterUpdater
                setSavecount={setSavecount}
                setupdateCounter={setupdateCounter}
                settotalCount={settotalCount}
            />


        </article>

    );
}
