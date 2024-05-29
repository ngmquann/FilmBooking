
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Card, CardBody, CardHeader, Col, Container, Input, Label, Row, FormGroup, Button } from 'reactstrap';
import "../CinemaCorner/css/CinemaCorner.css";

import RightColumn from "../CinemaCorner/RightColumn";

// Import Images
// import avatar3 from "../../../assets/images/users/avatar-3.jpg";
// import avatar4 from "../../../assets/images/users/avatar-4.jpg";

const BinhLuanPhim = () => {
    const SingleOptions = [
        { value: 'Watches', label: 'Watches' },
        { value: 'Headset', label: 'Headset' },
        { value: 'Sweatshirt', label: 'Sweatshirt' },
        { value: '20% off', label: '20% off' },
        { value: '4 star', label: '4 star' },
    ];

    const [selectedMulti, setSelectedMulti] = useState(null);

    const handleMulti = (selectedMulti) => {
        setSelectedMulti(selectedMulti);
    };

    const [selectedMovie, setSelectedMovie] = useState('');
    const [selectedTheater, setSelectedTheater] = useState('');
    const [selectedDate, setSelectedDate] = useState('');

    const handleMovieChange = (event) => {
        setSelectedMovie(event.target.value);
    };

    const handleTheaterChange = (event) => {
        setSelectedTheater(event.target.value);
    };

    const handleDateChange = (event) => {
        setSelectedDate(event.target.value);
    };

    document.title = "Diễn Viên";
    const data = [
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
        'Thor Ragnarock',
        'Thor Ragnarock 3',
        'Thor Ragnarock 4',
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
        '[Review] Furiosa Câu Chuyện Từ Max Điên: Bom Tấn Đỉnh Cao Từ Thương Hiệu Hơn 40 Năm',
    ];

    return (
        <React.Fragment>
            <Container style={{ paddingTop: 100 }}>
                <div className="page-content">
                    <Container fluid>

                        <Row>
                            <Col lg={8}>
                                <div fluid className="director-header-container-cinemaCorner">
                                    <Row>
                                        <Col md="4" className="d-flex">
                                            <Input type="select" className="custom-select-cinemaCorner mx-2" style={{ cursor: "pointer", fontSize: "12px", width: "auto" }}>
                                                <option>Tất Cả</option>
                                                <option>Option 1</option>
                                                <option>Option 2</option>
                                            </Input>
                                            <Input type="select" className="custom-select-cinemaCorner mx-2" style={{ cursor: "pointer", fontSize: "12px", width: "auto" }}>
                                                <option>Đang Chiếu/Sắp Chiếu</option>
                                                <option>Option 1</option>
                                                <option>Option 2</option>
                                            </Input>
                                            <Input type="select" className="custom-select-cinemaCorner mx-2" style={{ cursor: "pointer", fontSize: "12px", width: "auto" }}>
                                                <option>Mới Nhất</option>
                                                <option>Option 1</option>
                                                <option>Option 2</option>
                                            </Input>
                                        </Col>
                                    </Row>
                                    <div className="bottom-border"></div>
                                </div>
                                {data.map((title, index) => (
                                    <Col key={index} className="mb-4 mt-4">
                                        <Card className="h-100">
                                            <Row className="g-0">
                                                <Col md={4}>
                                                    <Link to="/binh-luan-phim/binh-luan-phim-details" style={{ textDecoration: "none", color: "inherit" }}>
                                                        <img
                                                            style={{ height: "250px", width: "250px", objectFit: "cover" }}
                                                            className="rounded w-100 h-auto"
                                                            src="https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcTtz0oynJ2nY-2wHZXerfJbJoGZpLIPrUSWdLfQbcWOuymbu2uWLbSyonfEeoUHbcnS1FE5k6ZfxkQe8YQ"
                                                            alt="Card"
                                                        />
                                                    </Link>
                                                </Col>
                                                <Col md={8}>
                                                    <CardHeader>
                                                        <h1 className="title-cinemaCorner-name mb-0">{title}</h1>
                                                    </CardHeader>
                                                    <CardBody>
                                                        <p className="card-text mb-2 text-muted" style={{fontFamily:"Arial",fontSize:"12px"}}>
                                                            For that very reason, I went on a quest and spoke to many different professional graphic designers and asked them what graphic design tips they live.
                                                        </p>
                                                        <p className="card-text">
                                                            <small className="text-muted">Last updated 3 mins ago</small>
                                                        </p>
                                                    </CardBody>
                                                </Col>
                                            </Row>
                                        </Card>
                                    </Col>
                                ))}
                            </Col>


                            {/* Bên Phải */}
                            <Col lg={4}>
                                <RightColumn />
                                <div className='button-dien-vien'>
                                    <Button color="secondary" outline className="waves-effect waves-light material-shadow-none" > Xem Thêm <i className="bx bx-right-arrow-alt"></i>  </Button>
                                </div>
                            </Col>
                        </Row>
                    </Container>
                </div>
            </Container>
        </React.Fragment>
    );
};

export default BinhLuanPhim;