import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

class CustomerFile extends Component {

    state = {
        // sheetForWork: '',
        // custNumber: '',
        // custName: '',
        columnForCheck: ''

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
    uploadColNumber = (columnForCheck) =>{
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
        // console.log(this.state.sheetForWork);
        // console.log(this.state.custNumber);
        // console.log(this.state.custName);
            this.uploadColNumber(this.state.columnForCheck)
            this.uploadFile(this.formData);


    };

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
                                <label htmlFor="sheetForWork">Tabellenblatt:</label>
                                <input type="text" className="form-control custInputWidth" id="sheetForWork"
                                    /* value={this.state.sheetForWork} onChange={this.handleInputChange}*//>
                            </div>
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
                                <label htmlFor="columnForCheck">Spalte zur Prüfung:</label>
                                <input type="number" className="form-control custInputWidth" id="columnForCheck" required
                                       value={this.state.columnForCheck}
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
                        <a href="#top">{this.formData}</a>
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
