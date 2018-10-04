import React, {Component} from 'react';
import './App.css';
import TouristPanel from './components/TouristPanel'
import FlightsPanel from './components/FlightsPanel'

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showTouristsPanel: true,
            showFlightsPanel: false
        };
    }

    onTouristBtnClick = () =>  {
        this.setState(
            {
                showTouristsPanel: !this.state.showTouristsPanel,
                showFlightsPanel: false
            }
        )
    };

    onFlightsBtnClick = () =>  {
        this.setState(
            {
                showTouristsPanel: false,
                showFlightsPanel: !this.state.showFlightsPanel
            }
        )
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <h1 className="App-title">Welcome to Spaceflight app</h1>
                </header>
                <div className="main">
                    <button
                        className="btn btn-primary marginRight"
                        onClick={this.onTouristBtnClick}
                >
                        Tourists

                    </button>
                    <button
                        className="btn btn-primary marginLeft"
                        onClick={this.onFlightsBtnClick}
                    >
                        Flights
                    </button>
                    {this.state.showTouristsPanel && <TouristPanel/>}
                    {this.state.showFlightsPanel && <FlightsPanel/>}
                </div>
            </div>
        );
    }
}

export default App;
