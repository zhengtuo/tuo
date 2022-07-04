import 'dart:async';

import 'package:flutter/material.dart';

import '../../main.dart';

class SplashPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _SplashPage();
  }
}

class _SplashPage extends State<SplashPage> {
  Timer? _timer;

  @override
  void initState() {
    super.initState();
    countDown();
  }
  @override
  void dispose() {
    super.dispose();
    _timer?.cancel();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: MediaQuery.of(context).size.width, // 屏幕宽度
      height: MediaQuery.of(context).size.height, // 屏幕高度
      child: Image.asset(
        "assets/images/f_splash.png",
        fit: BoxFit.cover,
      ),
    );
  }

  void countDown() {
    const requestPeriod = const Duration(seconds: 3);
    _timer = Timer(requestPeriod, () {
      Navigator.of(context).pop();
      //路由跳转
      Navigator.of(context).push(MaterialPageRoute(
          builder: (context) {
            return MyHomePage();
          }));
      _timer?.cancel();
    });
  }
}
