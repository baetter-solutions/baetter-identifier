import React, { useState } from "react";
import { AppBar, Box, Button, Grid, Tab, Tabs, Toolbar, Typography, useTheme, useMediaQuery } from "@mui/material";
import UploadFileIcon from '@mui/icons-material/UploadFile';
import { Link, useLocation } from "react-router-dom";
import DrawerComp from "./DrawerComp";

const Navbar_OLD = ({ links }) => {
    const theme = useTheme();
    const isMatch = useMediaQuery(theme.breakpoints.down('md'));
    const [value, setValue] = useState(0);
    const location = useLocation();

    React.useEffect(() => {
        const currentPath = location.pathname;
        const index = links.findIndex((link) => link.path === currentPath);
        setValue(index >= 0 ? index : 0);
    }, [location.pathname, links]);

    const handleTabChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <AppBar
            sx={{
                backgroundImage: 'linear-gradient(180deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 6%, rgba(0,212,255,1) 67%, rgba(16,149,0,1) 100%)'
            }}>
            <Toolbar>
                {isMatch ? (
                    <>
                        <Typography>
                            <UploadFileIcon />
                        </Typography>
                        <DrawerComp links={links} />
                    </>
                ) : (
                    <Grid sx={{ placeItems: "center" }} container>
                        <Grid item xs={2}>
                            <Typography>
                                <UploadFileIcon />
                            </Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <Tabs
                                indicatorColor="secondary"
                                textColor="inherit"
                                value={value}
                                onChange={handleTabChange} // Updated here
                            >
                                {links.map((link, index) => (
                                    <Tab key={index} label={link.label} component={Link} to={link.path} />
                                ))}
                            </Tabs>
                        </Grid>
                        <Grid item xs={1} />
                        <Grid item xs={3}>
                            <Box display="flex">
                                <Button
                                    sx={{ marginLeft: 'auto', background: 'rgba(16,149,0,1)' }}
                                    variant="contained">Button1</Button>
                                <Button
                                    sx={{ marginLeft: 1, background: 'rgba(16,149,0,1)' }}
                                    variant="contained">Button2</Button>
                            </Box>
                        </Grid>
                    </Grid>
                )}
            </Toolbar>
        </AppBar>
    );
};

export default Navbar_OLD;