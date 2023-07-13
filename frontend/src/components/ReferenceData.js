import React from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';

const ReferenceData = () => {
    /*  const [counters, setCounters] = useState({});

      useEffect(() => {
          fetch('/masterdataresponse')
              .then(response => response.json())
              .then(data => setCounters(data))
              .catch(error => console.error(error));
      }, []);*/

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

    /*const {MongoClient} = require('mongodb');
    const uri = process.env.REACT_APP_MONGODB_CONNECTION;

    const client = new MongoClient(uri, {useNewUrlParser: true, useUnifiedTopology: true});

    async function getTotalDocuments(){
        try {
            await client.connect();
            const database = client.db('dbidentifier');
            const collection = database.collection('masterdata');

            const totalDocuments = await collection.countDocuments();
            return totalDocuments;
        } finally {
            await client.close();
        }
    }
*/
    // let countOfDocuments = "getTotalDocuments()";

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
                    <p>Total number of articles: "countOfDocuments"</p>
                </div>
                <div className="div2ndlvl rounded border">
                    Total transmitted: "counters.saveCounter"<br/>
                    New: "counters.updateCounter"<br/>
                    Updated: "counters.totalCount"
                </div>
                <footer>
                    <h3>Implemented</h3>
                    On Drop -> Call to Backend <br/>
                    Convert from Excel to JSON <br/>
                    Look in DB, is it Article = exist ? POST : PUT;
                    <h3>Coming soon</h3>
                    installation of batch processing for large files <br/>
                    feedback of transmission <br/>
                    DB Status/Submit Display
                </footer>
            </div>

        </div>
    );
};

export default ReferenceData;
