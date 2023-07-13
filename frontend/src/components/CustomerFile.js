import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

class CustomerFile extends Component {

    state = {
        sheetForWork: '',
        custNumber: '',
        custName: ''
    };

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

    handleSubmit = (event) => {
        event.preventDefault();
        console.log(this.state.sheetForWork);
        console.log(this.state.custNumber);
        console.log(this.state.custName);
    };

    handleInputChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    };

    render() {
        return (
            <article className="mainstyle">
                <div className="divcontent">
                    <div className="div2ndlvl rounded border">

                        {/*<form onSubmit={this.handleSubmit}>
                            <p>Kundendetails</p>
                            <div className="form-container">
                                <label htmlFor="sheetForWork">Tabellenblatt:</label>
                                <input type="text" className="form-control custInputWidth" id="sheetForWork"
                                       value={this.state.sheetForWork} onChange={this.handleInputChange}/>
                            </div>
                            <div className="form-container">
                                <label htmlFor="custNumber">Kundennummer:</label>
                                <input type="number" className="form-control custInputWidth" id="custNumber"
                                       value={this.state.custNumber} onChange={this.handleInputChange} min="111111"
                                       max="999999"/>
                            </div>
                            <div className="form-container">
                                <label htmlFor="custName">Kundenname:</label>
                                <input type="text" className="form-control custInputWidth" id="custName"
                                       value={this.state.custName}
                                       onChange={this.handleInputChange}/>
                            </div>
                            <div className="form-container">
                                <div className="btnPosition">
                                    <button type="submit" className="btn btn-primary">Submit</button>
                                </div>
                            </div>
                        </form>*/}
                    </div>
                    <div className="div2ndlvl">
                        <Dropzone onDrop={this.onDrop}>
                            {({getRootProps, getInputProps, isDragActive}) => (
                                <div {...getRootProps()} className="dropzonestyle rounded border">
                                    <input {...getInputProps()} />
                                    {isDragActive
                                        ? "Drop it like it's hot!"
                                        : 'Klicken oder Datei mit der Maus darauf schieben'}

                                </div>
                            )}
                        </Dropzone>
                        {/*<a href="#top">{this.formData}</a>*/}
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
