import React, {Component, useEffect} from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';
import {alphabetOptions} from "./stuff/AlphabetOptions";

function FilepathUpdate({setPathfinishedfile}) {
    useEffect(() => {
        axios
            .get('http://localhost:8080/products/TranslatedFilepath')
            .then(response => {
                setPathfinishedfile(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, [setPathfinishedfile]);
    return null;
}

class CustomerFile extends Component {

    state = {
        custSheetnumber: '',
        custHeadline: '',
        columnWithNumberToIdentify: ''
    };

    formData = new FormData();

    onDrop = (acceptedFiles) => {
        this.formData.append('file', acceptedFiles[0]);
        // console.log(acceptedFiles[0].name)
    };

    uploadFile = (formData) => {
        axios
            .post('http://localhost:8080/upload', formData)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.uploadFile(this.formData)

        const {custSheetnumber, custHeadline, columnWithNumberToIdentify} = this.state;
        const requestData = {
            custSheetnumber: parseInt(custSheetnumber),
            custHeadline: parseInt(custHeadline),
            columnWithNumberToIdentify: parseInt(columnWithNumberToIdentify)
        };

        this.uploadToCustHandler(requestData);
    }

    uploadToCustHandler = (columnForCheck) => {
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
            })
    }

    handleInputChange = (event) => {
        const {id, value} = event.target;
        this.setState({[id]: value}, () => {
            // console.log(`Value of ${id}: ${value}`);
        })
    };


    render() {
        const { pathfinishedfile } = this.state;
        return (
            <article className="mainstyle">
                <div className="divcontent">
                    <div className="div2ndlvl rounded border ">
                        <form onSubmit={this.handleSubmit}>
                            Kundendetails
                            <div className="form-container hide">
                                <label htmlFor="custNumber">Kundennummer:</label>
                                <input type="number" className="form-control custInputWidth" id="custNumber"
                                    /*value={this.state.custNumber}
                                    onChange={this.handleInputChange}*/
                                       min="111111"
                                       max="999999"/>
                            </div>
                            <div className="form-container hide">
                                <label htmlFor="custName">Kundenname:</label>
                                <input type="text" className="form-control custInputWidth" id="custName"
                                    /* value={this.state.custName}
                                    onChange={this.handleInputChange}*//>
                            </div>
                            <div className="form-container">
                                <label htmlFor="custSheetnumber">Tabellenblatt:</label>
                                <input type="number" className="form-control custInputWidth" id="custSheetnumber"
                                       required
                                       value={this.state.custSheetnumber}
                                       onInput={this.handleInputChange}
                                       min="1"
                                       max="20"
                                />
                            </div>
                            <div className="form-container">
                                <label htmlFor="custHeadline">Zeile der Überschrift:</label>
                                <input type="number" className="form-control custInputWidth" id="custHeadline" required
                                       value={this.state.custHeadline}
                                       onInput={this.handleInputChange}
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
                                    value={this.state.columnWithNumberToIdentify}
                                    onChange={this.handleInputChange}
                                >
                                    {alphabetOptions.map(option => (
                                        <option key={option.value} value={option.value}>{option.label}</option>
                                    ))}
                                </select>
                            </div>
                            <div className="btnPosition">
                                <button type="submit" className="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                    <div className="div2ndlvl" id="droppi">
                        <Dropzone onDrop={this.onDrop}>
                            {({getRootProps, getInputProps, isDragActive}) => (
                                <div {...getRootProps()}
                                     className="dropzonestyle rounded border shadow-sm  bg-body-tertiary">
                                    <input {...getInputProps()} />
                                    {isDragActive
                                        ? "Drop it like it's hot!"
                                        : this.formData.get('file') ?
                                            <div>Ausgewählte Datei: <br/> {this.formData.get('file').name}
                                            </div> : 'Klicken oder Datei mit der Maus darauf schieben'}

                                </div>
                            )}
                        </Dropzone>
                    </div>
                    <div className="div2ndlvl" id="fertig">
                        <div className="dropzonestyle rounded border shadow-sm  bg-body-tertiary">
                            Download File: <a href="#top"> {pathfinishedfile}</a>
                        </div>
                    </div>
                </div>
                <FilepathUpdate setPathfinishedfile={path => this.setState({ pathfinishedfile: path })} />
            </article>
        );
    }
}

export default CustomerFile;
