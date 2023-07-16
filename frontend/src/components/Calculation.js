import React, {useState} from "react";
import * as XLSX from "xlsx";


const Calculation = () => {
    const [excelData, setExcelData] = useState(null);

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        const reader = new FileReader();

        reader.onload = (event) => {
            const data = new Uint8Array(event.target.result);
            const workbook = XLSX.read(data, {type: 'array'});
            const worksheet = workbook.Sheets[workbook.SheetNames[0]];
            const jsonData = XLSX.utils.sheet_to_json(worksheet, {header: 1});

            setExcelData(jsonData);
        };

        reader.readAsArrayBuffer(file);
    };

    return (
        <div className="mainstyle">
            <div className="divcontent">
                <div className="div2ndlvl border rounded">
                    <input type="file" onChange={handleFileChange}/>
                </div>
            </div>
            {excelData && (
                <div className="div2ndlvl border rounded">
                    <table>
                        <tbody>
                        {excelData.map((row, rowIndex) => (
                            <tr key={rowIndex}>
                                {row.map((cell, cellIndex) => (
                                    <td key={cellIndex}>{cell}</td>
                                ))}
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default Calculation;
