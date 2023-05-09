import React, {useState} from "react";
import {AppBar, Grid, Tab, Tabs, Toolbar, Typography} from "@mui/material";
import UploadFileIcon from '@mui/icons-material/UploadFile';

const Navbar = ({links}) => {
    const [value, setValue] = useState()

        return <AppBar>
            <Toolbar>
                <Grid container>
                    <Grid item xs={2}>
                        <Typography>
                            <UploadFileIcon/>
                        </Typography>
                    </Grid>
                    <Grid item xs={5}>
                        <Tabs indicatorColor="secondary"
                              textColor="inherit"
                              value={value}
                              onChange={(e, val) => setValue(val)}>
                            {links.map((link, index) => (
                                <Tab key={index} label={link}/>
                            ))}
                        </Tabs>
                    </Grid>
                </Grid>
            </Toolbar>
        </AppBar>
            ;
    };
export default Navbar