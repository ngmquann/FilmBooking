import 'dart:async';

import '../../data/models/home.dart';
import '../../data/responsitories/homeResponsitories.dart';

class HomeViewModel {
  final StreamController<HomeDataModel> _homeStreamController =
      StreamController<HomeDataModel>();

  Stream<HomeDataModel> get homeStream => _homeStreamController.stream;

  void loadHome() {
    final repository = HomeResponsetories();
    repository.getHomeData()?.then((value) {
      if (value != null) {
        _homeStreamController.add(value);
      }

    });
  }

  void dispose() {
    _homeStreamController.close();
  }
}
