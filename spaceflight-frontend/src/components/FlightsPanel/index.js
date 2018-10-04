import React, {Component} from 'react';
import './style.css';
import axios from 'axios'

class FlightPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {
            flights: [],
            showFlightList: true
        };

    }

    componentDidMount() {
        const endpoint = 'http://localhost:8080';
        axios.get(endpoint + '/flights')
            .then((response) => {
                console.log(response.data);
                this.setState({
                    flights: response.data
                });
            });
    }

     addButtonClick = () => {
            this.setState({
                ...this.state,
                showFlightList: false
            });
        };


          render() {
                return (
                    <div>
                        {this.state.showFlightList && this.createFlightView()}
                        {!this.state.showFlightList && this.createAddFlightView()}
                        <div className="btn btn-primary"
                             onClick={this.addButtonClick}
                        >
                            Add flight
                        </div>
                    </div>
                )
            }

     createFlightView() {
               return <table className="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Price</th>
                        <th scope="col">Departure</th>
                        <th scope="col">Arrival</th>
                        <th scope="col">Seat number</th>
                    </tr>
                    </thead>
                    <tbody>{
                        this.state.flights.map((flight, i) =>
                            (
                                <tr key={i}>
                                    <th scope="row">{i}</th>
                                    <td>{flight.price}</td>
                                    <td>{flight.departureTime}</td>
                                    <td>{flight.arrivalTime}</td>
                                    <td>{flight.numberOfSeats}</td>
                                </tr>
                            ))
                    }
                    </tbody>
                </table>
    }


createAddFlightView() {
        return (
            <div>
                <h3>Add Flight Panel</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Departure time</label>
                        <input className="form-control" placeholder="Departure time" onChange={this.handleOnDepartureTimeChange}/>
                    </div>
                    <div className="form-group">
                        <label>Arrival time</label>
                        <input className="form-control" placeholder="Arrival time" onChange={this.handleOnArrivalTimeChange}/>
                    </div>
                    <div className="form-group">
                        <label>Number of seats</label>
                        <input className="form-control" placeholder="Number of seats" onChange={this.handleOnSeatsChange}/>
                    </div>
                    <div className="form-group">
                        <label>Price</label>
                        <input className="form-control" placeholder="Price" onChange={this.handleOnPriceChange}/>
                    </div>

                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>

            </div>
        );
    }

    handleOnDepartureTimeChange = (e) => {
        this.setState({
            ...this.state,
            departureTime: e.target.value
        })
    }

    handleOnArrivalTimeChange = (e) => {
        this.setState({
            ...this.state,
            arrivalTime: e.target.value
        })
    }

    handleOnSeatsChange = (e) => {
        this.setState({
            ...this.state,
            numberOfSeats: e.target.value
        })
    }

    handleOnPriceChange = (e) => {
        this.setState({
            ...this.state,
            price: e.target.value
        })
    }

    onSubmit = (e) => {
        axios.post('http://localhost:8080/flights',
            {
                "departureTime": this.state.departureTime,
                "arrivalTime": this.state.arrivalTime,
                "numberOfSeats": this.state.numberOfSeats,
                "price": this.state.price,
                "touristEntities": []
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
}
export default FlightPanel;
