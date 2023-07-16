import React, {useState} from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';
import {alphabetOptions} from "./stuff/AlphabetOptions";

/* WORK IN PROGRESS
function FilepathUpdate() {
    const [pathfinishedfile, setPathfinishedfile] = useState('');

    useEffect(() => {
        axios
            .get('http://localhost:8080/products/TranslatedFilepath')
            .then(response => {
                setPathfinishedfile(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    return null;
}*/

function CustomerFile() {
    const [custSheetnumber, setCustSheetnumber] = useState('');
    const [custHeadline, setCustHeadline] = useState('');
    const [columnWithNumberToIdentify, setColumnWithNumberToIdentify] = useState('');
    const [formData, setFormData] = useState(new FormData());
    /* WORK IN PROGRESS
     const [pathfinishedfile, setPathfinishedfile] = useState('');
*/
    const handleInputChange = (event) => {
        const {id, value} = event.target;
        if (id === 'custSheetnumber') {
            setCustSheetnumber(value);
        } else if (id === 'custHeadline') {
            setCustHeadline(value);
        } else if (id === 'columnWithNumberToIdentify') {
            setColumnWithNumberToIdentify(value);
        }
    };

    const onDrop = (acceptedFiles) => {
        const newFormData = new FormData();
        newFormData.append('file', acceptedFiles[0]);
        setFormData(newFormData);
    };

    const uploadFile = () => {
        axios
            .post('http://localhost:8080/upload', formData)
            .then((response) => {
                console.log(response.data);
                window.location.reload();
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        uploadFile();

        const requestData = {
            custSheetnumber: parseInt(custSheetnumber),
            custHeadline: parseInt(custHeadline),
            columnWithNumberToIdentify: parseInt(columnWithNumberToIdentify)
        };

        uploadToCustHandler(requestData);
    };

    const uploadToCustHandler = (columnForCheck) => {
        axios
            .post('http://localhost:8080/uploadToCustHandler', columnForCheck, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return (
        <article className="mainstyle">
            <div className="divcontent">
                <div className="div2ndlvl rounded border ">
                    <form onSubmit={handleSubmit}>
                        Kundendetails
                        <div className="form-container hide">
                            <label htmlFor="custNumber">Kundennummer:</label>
                            <input
                                type="number"
                                className="form-control custInputWidth"
                                id="custNumber"
                                min="111111"
                                max="999999"
                            />
                        </div>
                        <div className="form-container hide">
                            <label htmlFor="custName">Kundenname:</label>
                            <input
                                type="text"
                                className="form-control custInputWidth"
                                id="custName"
                            />
                        </div>
                        <div className="form-container">
                            <label htmlFor="custSheetnumber">Tabellenblatt:</label>
                            <input
                                type="number"
                                className="form-control custInputWidth"
                                id="custSheetnumber"
                                required
                                value={custSheetnumber}
                                onChange={handleInputChange}
                                min="1"
                                max="20"
                            />
                        </div>
                        <div className="form-container">
                            <label htmlFor="custHeadline">Zeile der Überschrift:</label>
                            <input
                                type="number"
                                className="form-control custInputWidth"
                                id="custHeadline"
                                required
                                value={custHeadline}
                                onChange={handleInputChange}
                                min="1"
                                max="500"
                            />
                        </div>
                        <div className="form-container">
                            <label htmlFor="columnWithNumberToIdentify">Spalte zur Prüfung:</label>
                            <select
                                className="form-control custInputWidth"
                                id="columnWithNumberToIdentify"
                                required
                                value={columnWithNumberToIdentify}
                                onChange={handleInputChange}
                            >
                                {alphabetOptions.map(option => (
                                    <option key={option.value} value={option.value}>{option.label}</option>
                                ))}
                            </select>
                        </div>
                        <div className="div2ndlvl" id="droppi">
                            <Dropzone onDrop={onDrop}>
                                {({getRootProps, getInputProps, isDragActive}) => (
                                    <div {...getRootProps()}
                                         className="dropzonestyle rounded border shadow-sm bg-body-tertiary">
                                        <input {...getInputProps()} />
                                        {isDragActive
                                            ? "Drop it like it's hot!"
                                            : formData.get('file')
                                                ? <div>Ausgewählte Datei: <br/> {formData.get('file').name}</div>
                                                : 'Klicken oder Datei mit der Maus darauf schieben'}
                                    </div>
                                )}
                            </Dropzone>
                        </div>
                        <div className="btnPosition">
                            <button type="submit" className="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>

                {/* WORK IN PROGRESS
                <div className="div2ndlvl hide" id="fertig">
                    <div className="dropzonestyle rounded border shadow-sm bg-body-tertiary">
                        <a href={"pathfinishedfile"}>Klick mich</a>
                    </div>
                </div>
                */}
            </div>
        </article>
    );
}

export default CustomerFile;
