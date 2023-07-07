import {NavLink} from 'react-router-dom'
export const Navbar = () => {

    return (
        <nav className="navbar navbar-expand-lg">
            <div className="container-fluid">
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
            <NavLink className="nav-link active" aria-current="page" to='/'>Home</NavLink>
            <NavLink className="nav-link" to='/CustomerFile'>Kundendatei</NavLink>
            <NavLink className="nav-link disabled" to='/Calculation'>Kalkulation</NavLink>
            <NavLink className="nav-link disabled" to='/Export'>Export</NavLink>
            <NavLink className="nav-link" to='/ReferenceData'>Stammdaten</NavLink>
            <NavLink className="nav-link disabled" to='/About'>About</NavLink>
                    </div>
                </div>
            </div>
        </nav>
    )
}
