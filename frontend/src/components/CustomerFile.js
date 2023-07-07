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
                    <p>
                    </p>
                    {/*
                <select className="form-select form-select-lg mb-3" multiple aria-label=".form-select-lg multiple select example">
                        <option defaultValue>Open this select menu</option>
                        {Array.from(Array(26), (_, index) => {
                            const letter = String.fromCharCode(65 + index);
                            return <option key={index} value={index}>{letter}</option>;
                        })}
                    </select>
                    */}
                    <form onSubmit={this.handleSubmit}>
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
                            <input type="text" className="form-control custInputWidth" id="custName" value={this.state.custName}
                                   onChange={this.handleInputChange}/>
                        </div>
                        <div className="form-container">
                            <button type="submit" className="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default CustomerFile;
