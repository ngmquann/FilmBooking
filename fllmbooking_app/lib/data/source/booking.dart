import 'package:fllmbooking_app/data/models/VoucherRequest.dart';
import 'package:fllmbooking_app/data/models/VoucherResponse.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import '../models/BookingSuccessInfo.dart';
import '../models/PaymentRequest.dart';
import '../models/bookingData.dart';
import '../models/seatbooked.dart';
import '../responsitories/TokenRepositories.dart';

abstract class BookingDataSource {
  Future<ShowTimeTableResponse?> getBookingTime(int id);

  Future<SeatBooked?> getSeatBooked(int id);

  Future<VoucherResponse> applyVoucher(VoucherRequest code);

  Future<BookingSuccessInfo> bookingPaypal(PaymentRequest paymentRequest);
}

class BookingData implements BookingDataSource {
  @override
  Future<ShowTimeTableResponse?> getBookingTime(int id) async {
    TokenRepositories tokenRepository = TokenRepositories();
    var token = await tokenRepository.getToken();

    if (token == null) {
      // Trường hợp token là null, ném một ngoại lệ hoặc trả về null
      throw Exception('User is not authenticated');
    }

    final url = 'http://10.0.2.2:8081/api/home/booking/v1/$id';
    final uri = Uri.parse(url);

    final response = await http.get(uri, headers: {
      'Authorization': 'Bearer $token',
    });

    if (response.statusCode == 200) {
      final bodyContent = utf8.decode(response.bodyBytes);
      var dataWrapper = jsonDecode(bodyContent) as Map<String, dynamic>;
      var data = dataWrapper['result'] as Map<String, dynamic>;
      return ShowTimeTableResponse.fromJson(data);
    } else if (response.statusCode == 401) {
      await tokenRepository.deleteToken();
      throw Exception('Unauthorized: Token might have expired.');
    } else {
      final errorBody = utf8.decode(response.bodyBytes);
      final errorData = jsonDecode(errorBody) as Map<String, dynamic>;
      throw Exception(errorData['message'] ?? 'Unknown error occurred');
    }
  }

  @override
  Future<SeatBooked?> getSeatBooked(int id) async {
    TokenRepositories tokenRepository = TokenRepositories();
    var token = await tokenRepository.getToken();

    if (token == null) {
      throw Exception('User is not authenticated');
    }

    final url = 'http://10.0.2.2:8081/api/home/booking/v1/seat-booked/$id';
    final uri = Uri.parse(url);

    final response = await http.get(uri, headers: {
      'Authorization': 'Bearer $token',
    });

    if (response.statusCode == 200) {
      final bodyContent = utf8.decode(response.bodyBytes);
      var dataWrapper = jsonDecode(bodyContent) as Map<String, dynamic>;
      var data = dataWrapper['result'] as Map<String, dynamic>;
      return SeatBooked.fromJson(data);
    } else if (response.statusCode == 401) {
      await tokenRepository.deleteToken();
      throw Exception('Unauthorized: Token might have expired.');
    } else {
      final errorBody = utf8.decode(response.bodyBytes);
      final errorData = jsonDecode(errorBody) as Map<String, dynamic>;
      throw Exception(errorData['message'] ?? 'Unknown error occurred');
    }
  }

  @override
  Future<VoucherResponse> applyVoucher(VoucherRequest code) async {
    TokenRepositories tokenRepository = TokenRepositories();
    var token = await tokenRepository.getToken();

    if (token == null) {
      throw Exception('User is not authenticated');
    }
    String url = 'http://10.0.2.2:8081/api/home/booking/v1/apply-voucher';
    // print(jsonEncode(code.toJson()));
    final response = await http.post(
      Uri.parse(url),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },
      body: jsonEncode(code.toJson()),
    );
    if (response.statusCode == 200) {
      final jsonData = json.decode(response.body);
      if (jsonData['result'] != null) {
        return VoucherResponse.fromJson(jsonData['result']);
      } else {
        throw Exception('Unexpected JSON structure');
      }
    } else {
      final errorBody = utf8.decode(response.bodyBytes);
      final errorData = jsonDecode(errorBody) as Map<String, dynamic>;
      // print('===============');
      // print(errorData['message']);
      // print('===============');
      throw Exception(errorData['message']);
    }
  }

  @override
  Future<BookingSuccessInfo> bookingPaypal(PaymentRequest paymentRequest) async {
    TokenRepositories tokenRepository = TokenRepositories();
    var token = await tokenRepository.getToken();
    if (token == null) {
      throw Exception('User is not authenticated');
    }
    String url = 'http://10.0.2.2:8081/api/payment/v1/booking_paypal';

    final response = await http.post(
      Uri.parse(url),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },
      body: jsonEncode(paymentRequest.toJson()),
    );

    if (response.statusCode == 200) {
      final jsonData = json.decode(response.body);
      if (jsonData['result'] != null) {
        return BookingSuccessInfo.fromJson(jsonData['result']);
      } else {
        throw Exception('Unexpected JSON structure');
      }
    } else {
      final errorBody = utf8.decode(response.bodyBytes);
      final errorData = jsonDecode(errorBody) as Map<String, dynamic>;
      // print('===============');
      // print(errorData['message']);
      // print('===============');
      throw Exception(errorData['message']);
    }
  }
}
