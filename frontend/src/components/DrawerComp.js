import React, {useState} from 'react'
import {Drawer, IconButton, List, ListItemButton, ListItemIcon, ListItemText} from "@mui/material";
import MenuOpenIcon from '@mui/icons-material/MenuOpen';
const DrawerComp = ({links}) =>{
    const [open, setOpen] = useState(false)
    return (
        <>
            <Drawer anchor={"right"} PaperProps={{
                sx:{backgroundColor:'rgba(0,212,255,1)'}
            }} open={open} onClose={()=>setOpen(false)}>
                <List>
                    {links.map((link,index)=>(
                        <ListItemButton onClick={()=> setOpen(false)} key={index} divider>
                            <ListItemIcon>
                                <ListItemText sx={{color:'white'}}>{link.label}</ListItemText>
                            </ListItemIcon>
                        </ListItemButton>
                    ))}
                </List>
            </Drawer>
            <IconButton sx={{marginLeft: "auto", color: "white"}} onClick={()=>setOpen(!open)}>
                <MenuOpenIcon/>
            </IconButton>
        </>
    )
}

export default DrawerComp