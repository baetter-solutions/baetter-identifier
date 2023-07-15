import {NavLink} from 'react-router-dom'

export const Navbar = () => {

    return (
        <ul className="nav nav-tabs navStyle">
            <li className="nav-item firstitemPad">
                <NavLink className="nav-link" aria-current="page" to='/'>Home</NavLink>
            </li>
            <li className="nav-item"><NavLink className="nav-link" to='/CustomerFile'>Kundendatei</NavLink></li>
            <li className="nav-item"><NavLink className="nav-link " to='/Calculation'>Kalkulation</NavLink>
            </li>
            <li className="nav-item"><NavLink className="nav-link disabled" to='/Export'>Export</NavLink></li>
            <li className="nav-item"><NavLink className="nav-link" to='/ReferenceData'>Stammdaten</NavLink></li>
            <li className="nav-item"><NavLink className="nav-link disabled" to='/About'>About</NavLink></li>
        </ul>
    )
}
