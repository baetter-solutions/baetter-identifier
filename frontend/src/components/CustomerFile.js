import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

class CustomerFile extends Component {

    state = {
        custSheetnumber: '',
        custHeadline: '',
        columnWithNumberToIdentify: ''
    };

    formData = new FormData();

    onDrop = (acceptedFiles) => {
        this.formData.append('file', acceptedFiles[0]);
        console.log(this.formData)
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
    uploadColNumber = (columnForCheck) => {
        axios
            .post('http://localhost:8080/columcheck', columnForCheck, {
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

    uploadToCustHandler = (columnForCheck)=> {
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
            this.setState({[event.target.id]: event.target.value});
        };


    render() {

        return (
            <article className="mainstyle">
                <div className="divcontent">
                    <div className="div2ndlvl rounded border ">

                        <form onSubmit={this.handleSubmit}>
                            <p>Kundendetails</p>
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
                                <input type="number" className="form-control custInputWidth" id="custSheetnumber" required
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
                                <input type="number" className="form-control custInputWidth"
                                       id="columnWithNumberToIdentify" required
                                       value={this.state.columnWithNumberToIdentify}
                                       onInput={this.handleInputChange}
                                       min="1"
                                       max="500"
                                />
                            </div>
                            <div className="btnPosition">
                                <button type="submit" className="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                    <div className="div2ndlvl">
                        <Dropzone onDrop={this.onDrop}>
                            {({getRootProps, getInputProps, isDragActive}) => (
                                <div {...getRootProps()}
                                     className="dropzonestyle rounded border shadow-sm  bg-body-tertiary">
                                    <input {...getInputProps()} />
                                    {isDragActive
                                        ? "Drop it like it's hot!"
                                        : 'Klicken oder Datei mit der Maus darauf schieben'}

                                </div>
                            )}
                        </Dropzone>
                    </div>
                    <footer>
                        <h3>Implemented</h3>
                        On Drop -> Call to Backend


                        <h3>Coming soon</h3>
                        EXCEL File preview to select specific sheet, <br/>
                        row number and details from top
                    </footer>
                </div>
            </article>
        );
    }
}

export default CustomerFile;
