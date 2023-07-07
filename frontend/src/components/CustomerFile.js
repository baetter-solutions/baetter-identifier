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
                </div>
                <div className="divcontent rounded border">
                    <p>Zu ergänzen: <br/>
                        und inkl. Pfad+Dateiname, wird ein JSON generiert und in die DB
                        gespeichert <br/>
                        Forumlarfelder [ ]
                    </p>

                    <select className="form-select form-select-lg mb-3" multiple
                            aria-label=".form-select-lg multiple select example">
                        <option defaultValue>Open this select menu</option>
                        <option value="0">A</option>
                        <option value="1">B</option>
                        <option value="2">C</option>
                        <option value="3">D</option>
                        <option value="4">E</option>
                        <option value="5">F</option>
                        <option value="6">G</option>
                        <option value="7">H</option>
                        <option value="8">I</option>
                        <option value="9">J</option>
                        <option value="10">K</option>
                        <option value="11">L</option>
                        <option value="12">M</option>
                        <option value="13">N</option>
                        <option value="14">O</option>
                        <option value="15">P</option>
                        <option value="16">Q</option>
                        <option value="17">R</option>
                        <option value="18">S</option>
                        <option value="19">T</option>
                        <option value="20">U</option>
                        <option value="21">V</option>
                        <option value="22">W</option>
                        <option value="23">X</option>
                        <option value="24">Y</option>
                        <option value="25">Z</option>
                    </select>
                    <form onSubmit={this.handleSubmit}>
                            <div className="label-container">
                                <label htmlFor="sheetForWork" className="col-sm-3 col-form-label">Welches
                                Tabellenblatt?</label>
                            </div>
                            <div className="input-container">
                                <input type="text" className="form-control custInputMaxWidth" id="sheetForWork"
                                       value={this.state.sheetForWork}
                                       onChange={this.handleInputChange}/>
                            </div>
                        <div className="mb-3 row">
                            <label htmlFor="custNumber" className="col-sm-3 col-form-label">Kundennummer</label>
                            <div className="col-sm-9">
                                <input type="number" className="form-control custInputMaxWidth" id="custNumber"
                                       value={this.state.custNumber}
                                       onChange={this.handleInputChange} min="0" max="999999"/>
                            </div>
                        </div>
                        <div className="mb-3 row">
                            <label htmlFor="custName" className="col-sm-3 col-form-label">Kundenname</label>
                            <div className="col-sm-9">
                                <input type="text" className="form-control custInputMaxWidth" id="custName"
                                       value={this.state.custName}
                                       onChange={this.handleInputChange}/>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-sm-9 offset-sm-3">
                                <button type="submit" className="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default CustomerFile;
