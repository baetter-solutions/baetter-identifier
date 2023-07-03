import {NavLink} from 'react-router-dom'
export const Navbar = () => {

    return (
        <nav>
            <NavLink to='/'>Home</NavLink>
            <NavLink to='/CustomerFile'>Kundendatei</NavLink>
            <NavLink to='/Calculation'>Kalkulation</NavLink>
            <NavLink to='/Export'>Export</NavLink>
            <NavLink to='/ReferenceData'>Stammdaten</NavLink>
            <NavLink to='/About'>About</NavLink>
        </nav>
    )
}
