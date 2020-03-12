(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
    typeof define === 'function' && define.amd ? define(factory) :
    (global.UplusApi = factory());
}(this, (function () { 'use strict';

    /*! *****************************************************************************
    Copyright (c) Microsoft Corporation. All rights reserved.
    Licensed under the Apache License, Version 2.0 (the "License"); you may not use
    this file except in compliance with the License. You may obtain a copy of the
    License at http://www.apache.org/licenses/LICENSE-2.0

    THIS CODE IS PROVIDED ON AN *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED
    WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A PARTICULAR PURPOSE,
    MERCHANTABLITY OR NON-INFRINGEMENT.

    See the Apache Version 2.0 License for specific language governing permissions
    and limitations under the License.
    ***************************************************************************** */
    /* global Reflect, Promise */

    var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };

    function __extends(d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    }

    var __assign = function() {
        __assign = Object.assign || function __assign(t) {
            for (var s, i = 1, n = arguments.length; i < n; i++) {
                s = arguments[i];
                for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p)) t[p] = s[p];
            }
            return t;
        };
        return __assign.apply(this, arguments);
    };

    function __awaiter(thisArg, _arguments, P, generator) {
        return new (P || (P = Promise))(function (resolve, reject) {
            function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
            function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
            function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
            step((generator = generator.apply(thisArg, _arguments || [])).next());
        });
    }

    function __generator(thisArg, body) {
        var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
        return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
        function verb(n) { return function (v) { return step([n, v]); }; }
        function step(op) {
            if (f) throw new TypeError("Generator is already executing.");
            while (_) try {
                if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
                if (y = 0, t) op = [op[0] & 2, t.value];
                switch (op[0]) {
                    case 0: case 1: t = op; break;
                    case 4: _.label++; return { value: op[1], done: false };
                    case 5: _.label++; y = op[1]; op = [0]; continue;
                    case 7: op = _.ops.pop(); _.trys.pop(); continue;
                    default:
                        if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                        if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                        if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                        if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                        if (t[2]) _.ops.pop();
                        _.trys.pop(); continue;
                }
                op = body.call(thisArg, _);
            } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
            if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
        }
    }

    function resultAdapter(response) {
        if (response === void 0) { response = {}; }
        return __assign({}, response, { retCode: response.retCode === '00000' ? '000000' : response.retCode, retInfo: response.retInfo, retData: response.retData || response.data });
    }

    var layerbox = null;
    function init() {
        layerbox = document.createElement('div');
        layerbox.setAttribute('style', 'display: none; background-color: rgba(0,0,0,.7); pointer-events: auto;' +
            'position: fixed; left: 0; top: 0;width: 100%; height: 100%;z-index: 2019');
        layerbox.innerHTML = '<div id="m-alert-wraper" style="position: relative;width: 80%;padding: 10px;' +
            'margin: 200px auto 0 auto;background-color: #fff; border-radius: 5px;' +
            'text-align: center;box-sizing: content-box;">' +
            '<div id="m-alert-content" style="padding: 10px;font-size: 14px;"></div>' +
            '<div><button id="m-alert-button" style="color: #fff;background-color: #108ee9;' +
            'border: 1px solid #108ee9;border-radius: 5px;padding: 6px 10px;width: 80px;">确定</button></div></div>';
        // append
        document.body.appendChild(layerbox);
    }
    // if not in browsers, break
    if (window && window.document) {
        init();
    }
    function alert(content, onOk) {
        if (window && window.document) {
            var ele_1 = window.document.querySelector('#m-alert-content');
            var but = window.document.querySelector('#m-alert-button');
            if (ele_1) {
                ele_1.innerHTML = content;
                layerbox.style.display = 'block';
            }
            if (but) {
                but.addEventListener('click', function () {
                    if (ele_1) {
                        ele_1.innerHTML = '';
                    }
                    layerbox.style.display = 'none';
                    if (typeof onOk === 'function') {
                        onOk();
                    }
                });
            }
        }
    }

    /**
     * 迭代器，用于接口调用失败后，延时1秒重试
     * 最多尝试10次
     * @param action 接口函数
     * @param data 接口要求的参数
     * @param resolve 成功回调
     * @param reject 失败回调
     * @param countNumber 当前迭代次数，可以不传，默认是0
     */
    function iteratorApiCloud(action, data, resolve, reject, countNumber) {
        if (countNumber === void 0) { countNumber = 0; }
        // 记步++
        countNumber++;
        action(data, function (ret, err) {
            if (ret && resolve) {
                resolve(resultAdapter(ret));
            }
            else if (reject) {
                if (countNumber < 11) {
                    window.setTimeout(function () { return iteratorApiCloud(action, data, resolve, reject, countNumber); }, 1000);
                }
                else {
                    reject(resultAdapter(err));
                    alert('页面加载失败，请退出重试', function () {
                        if (window && window.api && window.api.require('UpBaseModule')) {
                            window.api.require('UpBaseModule').closeView();
                        }
                    });
                }
            }
        });
    }
    function iteratorCordova(action, resolve, reject, countNumber) {
        if (countNumber === void 0) { countNumber = 0; }
        var data = [];
        for (var _i = 4; _i < arguments.length; _i++) {
            data[_i - 4] = arguments[_i];
        }
        // 记步++
        countNumber++;
        action.apply(void 0, [function (ret) { return resolve(resultAdapter(ret)); },
            function (err) {
                if (countNumber < 11) {
                    window.setTimeout(function () { return iteratorCordova.apply(void 0, [action, resolve, reject, countNumber].concat(data)); }, 1000);
                }
                else {
                    reject(resultAdapter(err));
                    alert('页面加载失败，请退出重试', function () {
                        if (window && window.cordova && window.cordova.require('uphybrid-plugin-core.upcore')) {
                            window.cordova.require('uphybrid-plugin-core.upcore').closeH5ContainerView();
                        }
                    });
                }
            }].concat(data));
    }

    var LogicEngineClass = /** @class */ (function () {
        function LogicEngineClass() {
            this.logicEngine = null;
            this.storeEngine = null;
            this.deviceMac = '';
            this.deviceChangeListener = null;
        }
        LogicEngineClass.prototype.setLogicEngine = function (logicEngine) {
            this.logicEngine = logicEngine;
        };
        LogicEngineClass.prototype.setStoreEngine = function (storeEngine) {
            this.storeEngine = storeEngine;
        };
        LogicEngineClass.prototype.setDeviceMac = function (deviceMac) {
            this.deviceMac = deviceMac;
        };
        LogicEngineClass.prototype.setDeviceChangeListener = function (deviceChangeListener) {
            this.deviceChangeListener = deviceChangeListener;
        };
        LogicEngineClass.prototype.getBaseInfoPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.logicEngine.getBaseInfo((ret: any) => resolve(resultAdapter(ret)),
                    //   (err: any) => reject(resultAdapter(err)), mac);
                    iteratorCordova(self.logicEngine.getBaseInfo.bind(self.logicEngine), resolve, reject, 0, mac);
                }
            });
        };
        LogicEngineClass.prototype.getInitialAttributeListPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.logicEngine.getInitialAttributeList((ret: any) => resolve(resultAdapter(ret)),
                    //   (err: any) => reject(resultAdapter(err)), mac);
                    iteratorCordova(self.logicEngine.getInitialAttributeList.bind(self.logicEngine), resolve, reject, 0, mac);
                }
            });
        };
        LogicEngineClass.prototype.getAttributeListPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.logicEngine.getAttributeList((ret: any) => resolve(resultAdapter(ret)),
                    //   (err: any) => reject(resultAdapter(err)), mac);
                    iteratorCordova(self.logicEngine.getAttributeList.bind(self.logicEngine), resolve, reject, 0, mac);
                }
            });
        };
        LogicEngineClass.prototype.getAttributeByNamePromise = function (mac, name) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.getAttributeByName(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, mac, name);
                }
            });
        };
        LogicEngineClass.prototype.getCautionsPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.logicEngine.getCautionList((ret: any) => resolve(resultAdapter(ret)),
                    //   (err: any) => reject(resultAdapter(err)), mac);
                    iteratorCordova(self.logicEngine.getCautionList.bind(self.logicEngine), resolve, reject, 0, mac);
                }
            });
        };
        LogicEngineClass.prototype.attachPromise = function (mac, deviceChangeListener) {
            var _this = this;
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // 先订阅
                    // self.logicEngine.attach(resolve, reject, mac, deviceChangeListener);
                    iteratorCordova(self.logicEngine.attach.bind(self.logicEngine), resolve, reject, 0, mac, deviceChangeListener);
                    // 然后监听resume事件
                    document.addEventListener('resume', function () { return __awaiter(_this, void 0, void 0, function () {
                        var baseInfo, attributes, cautions;
                        return __generator(this, function (_a) {
                            switch (_a.label) {
                                case 0: return [4 /*yield*/, self.getBaseInfoPromise(mac)];
                                case 1:
                                    baseInfo = _a.sent();
                                    return [4 /*yield*/, self.getAttributeListPromise(mac)];
                                case 2:
                                    attributes = _a.sent();
                                    return [4 /*yield*/, self.getCautionsPromise(mac)];
                                case 3:
                                    cautions = _a.sent();
                                    deviceChangeListener(mac, baseInfo.retData, attributes.retData, cautions.retData);
                                    self.logicEngine.attach(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, mac, deviceChangeListener);
                                    return [2 /*return*/];
                            }
                        });
                    }); }, false);
                }
            });
        };
        LogicEngineClass.prototype.detachPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.detach(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, mac);
                }
            });
        };
        LogicEngineClass.prototype.calculatePromise = function (mac, name, value, clearFlag) {
            if (clearFlag === void 0) { clearFlag = true; }
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof self.storeEngine === 'function') {
                    var command = { name: name, value: value };
                    self.storeEngine([command]);
                    resolve();
                }
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.calculate(function (success) {
                        // 成功回调
                        resolve(resultAdapter(success));
                        // 使用自定义的console方法
                        if (typeof window.consoleLog === 'function') {
                            window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u6210\u529F--name:" + name + ",value:" + value);
                        }
                    }, function (error) {
                        // 失败回调
                        reject(resultAdapter(error));
                        // 使用自定义的console方法
                        if (typeof window.consoleLog === 'function') {
                            window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u5931\u8D25--name:" + name + ",value:" + value + ", error--", error);
                        }
                    }, mac, name, "" + value, clearFlag);
                }
            });
        };
        LogicEngineClass.prototype.calculateAllPromise = function (mac, commands, clearFlag) {
            if (clearFlag === void 0) { clearFlag = true; }
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof self.storeEngine === 'function') {
                    self.storeEngine(commands);
                    resolve();
                }
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.calculateAll(function (success) {
                        // 成功回调
                        resolve(resultAdapter(success));
                        // 使用自定义的console方法
                        if (typeof window.consoleLog === 'function') {
                            window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u6210\u529F--" + JSON.stringify(commands));
                        }
                    }, function (error) {
                        // 失败回调
                        reject(resultAdapter(error));
                        // 使用自定义的console方法
                        if (typeof window.consoleLog === 'function') {
                            window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u5931\u8D25--" + JSON.stringify(commands) + ", error--", error);
                        }
                    }, mac, commands, clearFlag);
                }
            });
        };
        LogicEngineClass.prototype.operatePromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.operate(function (success) {
                        // 成功回调
                        resolve(resultAdapter(success));
                        // 使用自定义的console方法
                        if (typeof window.consoleLog === 'function') {
                            window.consoleLog("\u4E0B\u53D1\u5C5E\u6027\u6210\u529F");
                        }
                    }, function (error) {
                        // 失败回调
                        reject(resultAdapter(error));
                        // 使用自定义的console方法
                        if (typeof window.consoleLog === 'function') {
                            window.consoleLog("\u4E0B\u53D1\u5C5E\u6027\u5931\u8D25--error--", error);
                        }
                    }, mac);
                }
            });
        };
        return LogicEngineClass;
    }());

    var layerbox$1 = null;
    function init$1() {
        layerbox$1 = document.createElement('div');
        layerbox$1.setAttribute('style', 'display: none; background-color: rgba(0,0,0,.7); pointer-events: auto;' +
            'position: fixed; left: 0; top: 0;width: 100%; height: 100%;z-index: 2019');
        layerbox$1.innerHTML = '<div id="m-layer-content" style="position: relative;width: 80%;padding: 10px;' +
            'margin: 200px auto 0 auto;background-color: #fff;font-size: 14px;border-radius: 5px;' +
            'text-align: center;box-sizing: content-box;"></div>';
        // append
        document.body.appendChild(layerbox$1);
        // addEventListener
        layerbox$1.addEventListener('click', function () {
            var ele = document.querySelector('#m-layer-content');
            if (ele) {
                ele.innerHTML = '';
            }
            layerbox$1.style.display = 'none';
        });
    }
    // if not in browsers, break
    if (window && window.document) {
        init$1();
    }
    function toast(content) {
        if (window && window.document) {
            var ele = window.document.querySelector('#m-layer-content');
            if (ele) {
                ele.innerHTML = content;
            }
            layerbox$1.style.display = 'block';
        }
    }

    var UserClass = /** @class */ (function () {
        function UserClass() {
            this.userEngine = null;
        }
        UserClass.prototype.setUserEngine = function (userEngine) {
            this.userEngine = userEngine;
        };
        UserClass.prototype.getLoginStatus = function () {
            return new Promise(function (reject) {
                toast('cordova 容器下没有实现此接口--getLoginStatus');
                reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
            });
        };
        UserClass.prototype.getUserInfo = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.userEngine) {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
                else {
                    self.userEngine.getUserInfo(function (data) { return resolve(resultAdapter(data)); }, function (data) { return reject(resultAdapter(data)); });
                }
            });
        };
        UserClass.prototype.getSignCode = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                var bodyObj = data.data;
                if (typeof bodyObj === 'string') {
                    try {
                        bodyObj = JSON.parse(data.data);
                    }
                    catch (_a) { }
                }
                var bodyData = { timestamp: data.timestamp, data: bodyObj };
                if (self.userEngine) {
                    self.userEngine.getSignCode(function (response) { return resolve(resultAdapter(response)); }, function (response) { return reject(resultAdapter(response)); }, bodyData);
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        UserClass.prototype.getCommonSignCode = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.userEngine) {
                    self.userEngine.getSign(function (response) { return resolve(resultAdapter(response)); }, function (response) { return reject(resultAdapter(response)); }, data);
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        UserClass.prototype.getCommonSignCodeAndBody = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.userEngine) {
                    self.userEngine.getSignAndBody(function (response) { return resolve(resultAdapter(response)); }, function (response) { return reject(resultAdapter(response)); }, data);
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        return UserClass;
    }());

    var UpdeviceClass = /** @class */ (function () {
        function UpdeviceClass() {
            // 设备引擎
            this.deviceEngine = null;
        }
        // 设置设备引擎
        UpdeviceClass.prototype.setDeviceEngine = function (deviceEngine) {
            this.deviceEngine = deviceEngine;
        };
        UpdeviceClass.prototype.getDeviceList = function (data) {
            if (data === void 0) { data = {}; }
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getNewDeviceList(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, data.familyId ? data.familyId : null);
                }
            });
        };
        UpdeviceClass.prototype.getDeviceInfoById = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine || !data) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getDeviceInfo(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, data.deviceId);
                }
            });
        };
        UpdeviceClass.prototype.getSubDevList = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getSubDevList(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, data.deviceId);
                }
            });
        };
        UpdeviceClass.prototype.getSubDevInfoById = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getSubDevInfo(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, data.deviceId, data.subDevId);
                }
            });
        };
        UpdeviceClass.prototype.executeOperation = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.execDeviceOperation(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, data.subDevId || data.deviceId, data.command, data.groupName);
                }
            });
        };
        UpdeviceClass.prototype.subscribeResource = function () {
            toast('cordova 容器下没有实现此接口--subscribeResource');
            throw Error('upbleEngine is not defined!!!');
        };
        UpdeviceClass.prototype.unsubscribeResource = function () {
            toast('cordova 容器下没有实现此接口--unsubscribeResource');
            throw Error('upbleEngine is not defined!!!');
        };
        UpdeviceClass.prototype.initListeners = function (data) {
            try {
                // 订阅
                this.deviceEngine.subscribeNewDeviceListChange();
                this.deviceEngine.subscribeNewDeviceChange(null, null, data.deviceId);
                this.deviceEngine.subscribeNewSubDevChange(null, null, data.deviceId);
            }
            catch (_a) { }
            // 注册监听器
            window.publishNewDeviceChange = function (mac, deviceInfo) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (mac && typeof data.deviceInfoListener === 'function') {
                    data.deviceInfoListener(mac, deviceInfo);
                }
            };
            window.publishNewDeviceListChange = function (deviceList) {
                if (deviceList === void 0) { deviceList = []; }
                if (typeof data.deviceListListener === 'function') {
                    data.deviceListListener(deviceList);
                }
            };
            window.publishNewSubDevChange = function (mac, deviceInfo) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (mac && typeof data.subDeviceInfoListener === 'function') {
                    data.subDeviceInfoListener(mac, deviceInfo);
                }
            };
            window.publishNewSubDevListChange = function (mac, deviceList) {
                if (deviceList === void 0) { deviceList = []; }
                if (typeof data.subDeviceListListener === 'function') {
                    data.subDeviceListListener(mac, deviceList);
                }
            };
            window.publishNewDeviceAlarm = function (mac, deviceInfo, alarmList) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (alarmList === void 0) { alarmList = []; }
                if (typeof data.subDeviceListListener === 'function') {
                    data.deviceAlarmListener(mac, deviceInfo, alarmList);
                }
            };
            window.publishNewSubDevAlarm = function (mac, deviceInfo, alarmList) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (alarmList === void 0) { alarmList = []; }
                if (typeof data.subDeviceListListener === 'function') {
                    data.subDeviceAlarmListener(mac, deviceInfo, alarmList);
                }
            };
        };
        return UpdeviceClass;
    }());

    var VdnClass = /** @class */ (function () {
        function VdnClass() {
            this.vdnEngine = null;
        }
        VdnClass.prototype.setVdnEngine = function (vdnEngine) {
            this.vdnEngine = vdnEngine;
        };
        VdnClass.prototype.goToPage = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.vdnEngine) {
                    self.vdnEngine.goToPage(function (response) { return resolve(resultAdapter(response)); }, function (response) { return reject(resultAdapter(response)); }, param);
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        VdnClass.prototype.goBack = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.vdnEngine) {
                    self.vdnEngine.goBack(function (response) { return resolve(resultAdapter(response)); }, function (response) { return reject(resultAdapter(response)); });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        return VdnClass;
    }());

    var UmshareClass = /** @class */ (function () {
        function UmshareClass() {
            this.umshareEngine = null;
        }
        UmshareClass.prototype.setUmshareEngine = function (umshareEngine) {
            this.umshareEngine = umshareEngine;
        };
        UmshareClass.prototype.showShareView = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (data.shareType !== 'webPage') {
                    toast('cordova 容器仅支持 webPage 类型');
                    reject(resultAdapter({ retInfo: 'cordova 容器仅支持 webPage 类型' }));
                    return;
                }
                if (self.umshareEngine) {
                    self.umshareEngine.showShareView(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, __assign({}, data, { image: data.thumImage }));
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        return UmshareClass;
    }());

    var UplocationClass = /** @class */ (function () {
        function UplocationClass() {
            this.uplocationEngine = null;
        }
        UplocationClass.prototype.setUplocationEngine = function (uplocationEngine) {
            this.uplocationEngine = uplocationEngine;
        };
        UplocationClass.prototype.getLocation = function () {
            toast('cordova 容器下没有实现此接口--getLocation');
            throw Error('uplocationEngine is not defined!!!');
        };
        return UplocationClass;
    }());

    function adjustRetData(data, resolve, reject) {
        if (data && data.data) {
            var responseData = data.data;
            if (Object.prototype.toString.call(data.data) !== '[object Object]') {
                responseData = JSON.parse(data.data);
            }
            resolve(__assign({}, responseData, { retCode: responseData.retCode, retInfo: responseData.retInfo, retData: responseData.data }));
        }
        else if (data) {
            resolve({
                retCode: '',
                retInfo: '',
                retData: data,
            });
        }
        reject({
            retCode: '',
            retInfo: '未知错误',
            retData: data,
        });
    }
    var HttpClass = /** @class */ (function () {
        function HttpClass(uplusApiInstance) {
            this.httpEngine = null;
            this.uplusApiInstance = uplusApiInstance;
        }
        HttpClass.prototype.setHttpEngine = function (httpEngine) {
            this.httpEngine = httpEngine;
        };
        HttpClass.prototype.get = function (options) {
            var _this = this;
            // 打点开始
            this.statPageEventData({
                tagName: "net_" + options.url + "_start",
                timeStamp: +(new Date()),
            });
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.httpEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.httpEngine.get(options.url, options.data, options.headers, function (data) {
                        // 打点结束
                        _this.statPageEventData({
                            tagName: "net_" + options.url + "_end",
                            timeStamp: +(new Date()),
                        });
                        // 更新track记录
                        _this.updateTrackUrlStatus(options.url, 'get');
                        adjustRetData(data, resolve, reject);
                    }, function (data) { return adjustRetData(data, resolve, reject); });
                }
            });
        };
        HttpClass.prototype.post = function (options) {
            var _this = this;
            // 打点开始
            this.statPageEventData({
                tagName: "net_" + options.url + "_start",
                timeStamp: +(new Date()),
            });
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.httpEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.httpEngine.post(options.url, options.data, options.headers, function (data) {
                        // 打点结束
                        _this.statPageEventData({
                            tagName: "net_" + options.url + "_end",
                            timeStamp: +(new Date()),
                        });
                        // 更新track记录
                        _this.updateTrackUrlStatus(options.url, 'post');
                        adjustRetData(data, resolve, reject);
                    }, function (data) { return adjustRetData(data, resolve, reject); });
                }
            });
        };
        HttpClass.prototype.head = function (options) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.httpEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.httpEngine.head(options.url, options.data, options.headers, function (data) { return adjustRetData(data, resolve, reject); }, function (data) { return adjustRetData(data, resolve, reject); });
                }
            });
        };
        HttpClass.prototype.uploadFile = function (options) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.httpEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.httpEngine.uploadFile(options.url, options.data, options.headers, options.filePath, options.name || '', function (data) { return adjustRetData(data, resolve, reject); }, function (data) { return adjustRetData(data, resolve, reject); });
                }
            });
        };
        HttpClass.prototype.downloadFile = function (options) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.httpEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.httpEngine.downloadFile(options.url, options.data, options.headers, options.filePath, function (data) { return adjustRetData(data, resolve, reject); }, function (data) { return adjustRetData(data, resolve, reject); });
                }
            });
        };
        HttpClass.prototype.statPageEventData = function (params) {
            if (this.uplusApiInstance && this.uplusApiInstance.upTraceModule) {
                this.uplusApiInstance.upTraceModule.statPageEventData(params).catch(function () { return null; });
            }
        };
        HttpClass.prototype.updateTrackUrlStatus = function (url, method) {
            if (this.uplusApiInstance && this.uplusApiInstance.upTraceModule) {
                var observeUrls = this.uplusApiInstance.upTraceModule.pageTrackObj.observeUrls || [];
                for (var i = 0; i < observeUrls.length; i++) {
                    if (observeUrls[i] && observeUrls[i].url === url && observeUrls[i].method === method) {
                        observeUrls[i].status = 'resolve';
                        break;
                    }
                }
            }
        };
        return HttpClass;
    }());

    var UpResourceClass = /** @class */ (function () {
        function UpResourceClass() {
        }
        UpResourceClass.prototype.setUpresourceEngine = function (upresourceEngine) {
            this.upresourceEngine = upresourceEngine;
        };
        UpResourceClass.prototype.update = function () {
            toast('cordova 容器下没有实现此接口--update');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.updateDeviceConfig = function () {
            toast('cordova 容器下没有实现此接口--updateDeviceConfig');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.getPresentInfoList = function () {
            toast('cordova 容器下没有实现此接口--getPresentInfoList');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.getLatestInfoList = function () {
            toast('cordova 容器下没有实现此接口--getLatestInfoList');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.getActiveInfoList = function () {
            toast('cordova 容器下没有实现此接口--getActiveInfoList');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.install = function () {
            toast('cordova 容器下没有实现此接口--install');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.uninstall = function () {
            toast('cordova 容器下没有实现此接口--uninstall');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.upgrade = function () {
            toast('cordova 容器下没有实现此接口--upgrade');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.cancelDownload = function () {
            toast('cordova 容器下没有实现此接口--cancelDownload');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.resumeDownload = function () {
            toast('cordova 容器下没有实现此接口--resumeDownload');
            throw Error('upresourceEngine is not defined!!!');
        };
        UpResourceClass.prototype.cancel = function () {
            toast('cordova 容器下没有实现此接口--cancel');
            throw Error('upresourceEngine is not defined!!!');
        };
        return UpResourceClass;
    }());

    var UpNetworkClass = /** @class */ (function () {
        function UpNetworkClass() {
        }
        UpNetworkClass.prototype.setUpnetworkEngine = function (upnetworkEngine) {
            this.upnetworkEngine = upnetworkEngine;
        };
        UpNetworkClass.prototype.isOnline = function () {
            toast('cordova 容器下没有实现此接口--isOnline');
            throw Error('upnetworkEngine is not defined!!!');
        };
        UpNetworkClass.prototype.getNetworkInfo = function () {
            toast('cordova 容器下没有实现此接口--getNetworkInfo');
            throw Error('upnetworkEngine is not defined!!!');
        };
        UpNetworkClass.prototype.getWifiMacAddress = function () {
            toast('cordova 容器下没有实现此接口--getWifiMacAddress');
            throw Error('upnetworkEngine is not defined!!!');
        };
        return UpNetworkClass;
    }());

    var UpFsClass = /** @class */ (function () {
        function UpFsClass() {
        }
        UpFsClass.prototype.setUpfsEngine = function (upfsEngine) {
            this.upfsEngine = upfsEngine;
        };
        UpFsClass.prototype.createDir = function () {
            toast('cordova 容器下没有实现此接口--createDir');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.rmdir = function () {
            toast('cordova 容器下没有实现此接口--rmdir');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.createFile = function () {
            toast('cordova 容器下没有实现此接口--createFile');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.remove = function () {
            toast('cordova 容器下没有实现此接口--remove');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.copyTo = function () {
            toast('cordova 容器下没有实现此接口--copyTo');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.moveTo = function () {
            toast('cordova 容器下没有实现此接口--moveTo');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.rename = function () {
            toast('cordova 容器下没有实现此接口--rename');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readDir = function () {
            toast('cordova 容器下没有实现此接口--readDir');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.open = function () {
            toast('cordova 容器下没有实现此接口--open');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.read = function () {
            toast('cordova 容器下没有实现此接口--read');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readUp = function () {
            toast('cordova 容器下没有实现此接口--readUp');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readDown = function () {
            toast('cordova 容器下没有实现此接口--readDown');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.write = function () {
            toast('cordova 容器下没有实现此接口--write');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.close = function () {
            toast('cordova 容器下没有实现此接口--close');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.exist = function () {
            toast('cordova 容器下没有实现此接口--exist');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.getAttribute = function () {
            toast('cordova 容器下没有实现此接口--getAttribute');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readByLength = function () {
            toast('cordova 容器下没有实现此接口--readByLength');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.writeByLength = function () {
            toast('cordova 容器下没有实现此接口--writeByLength');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.getMD5 = function () {
            toast('cordova 容器下没有实现此接口--getMD5');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.createDirSync = function () {
            toast('cordova 容器下没有实现此接口--createDirSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.rmdirSync = function () {
            toast('cordova 容器下没有实现此接口--rmdirSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.createFileSync = function () {
            toast('cordova 容器下没有实现此接口--createFileSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.removeSync = function () {
            toast('cordova 容器下没有实现此接口--removeSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.copyToSync = function () {
            toast('cordova 容器下没有实现此接口--copyToSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.moveToSync = function () {
            toast('cordova 容器下没有实现此接口--moveToSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.renameSync = function () {
            toast('cordova 容器下没有实现此接口--renameSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readDirSync = function () {
            toast('cordova 容器下没有实现此接口--readDirSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.openSync = function () {
            toast('cordova 容器下没有实现此接口--openSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readSync = function () {
            toast('cordova 容器下没有实现此接口--readSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readUpSync = function () {
            toast('cordova 容器下没有实现此接口--readUpSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readDownSync = function () {
            toast('cordova 容器下没有实现此接口--readDownSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.writeSync = function () {
            toast('cordova 容器下没有实现此接口--writeSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.closeSync = function () {
            toast('cordova 容器下没有实现此接口--closeSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.existSync = function () {
            toast('cordova 容器下没有实现此接口--existSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.getAttributeSync = function () {
            toast('cordova 容器下没有实现此接口--getAttributeSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.readByLengthSync = function () {
            toast('cordova 容器下没有实现此接口--readByLengthSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.writeByLengthSync = function () {
            toast('cordova 容器下没有实现此接口--writeByLengthSync');
            throw Error('upfsEngine is not defined!!!');
        };
        UpFsClass.prototype.getMD5Sync = function () {
            toast('cordova 容器下没有实现此接口--getMD5Sync');
            throw Error('upfsEngine is not defined!!!');
        };
        return UpFsClass;
    }());

    var UpAudioClass = /** @class */ (function () {
        function UpAudioClass() {
        }
        UpAudioClass.prototype.setUpAudio = function (upAudio) {
            this.upAudio = upAudio;
        };
        UpAudioClass.prototype.initPlayer = function () {
            toast('cordova 容器下没有实现此接口--initPlayer');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.play = function () {
            toast('cordova 容器下没有实现此接口--play');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.pause = function () {
            toast('cordova 容器下没有实现此接口--pause');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.stop = function () {
            toast('cordova 容器下没有实现此接口--stop');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.setVolume = function () {
            toast('cordova 容器下没有实现此接口--setVolume');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.getVolume = function () {
            toast('cordova 容器下没有实现此接口--getVolume');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.setCurrent = function () {
            toast('cordova 容器下没有实现此接口--setCurrent');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.getCurrent = function () {
            toast('cordova 容器下没有实现此接口--getCurrent');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.getState = function () {
            toast('cordova 容器下没有实现此接口--getState');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.getBufferRatio = function () {
            toast('cordova 容器下没有实现此接口--getBufferRatio');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.addEventListener = function () {
            toast('cordova 容器下没有实现此接口--addEventListener');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.removeEventListener = function () {
            toast('cordova 容器下没有实现此接口--removeEventListener');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.clearCache = function () {
            toast('cordova 容器下没有实现此接口--clearCache');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.scanAudioLibrary = function () {
            toast('cordova 容器下没有实现此接口--scanAudioLibrary');
            throw Error('upAudio is not defined!!!');
        };
        UpAudioClass.prototype.getAttr = function () {
            toast('cordova 容器下没有实现此接口--getAttr');
            throw Error('upAudio is not defined!!!');
        };
        return UpAudioClass;
    }());

    var UppedometernClass = /** @class */ (function () {
        function UppedometernClass() {
            this.uppedometerEngine = null;
        }
        UppedometernClass.prototype.setUppedometerEngine = function (uppedometerEngine) {
            this.uppedometerEngine = uppedometerEngine;
        };
        UppedometernClass.prototype.startCount = function () {
            toast('cordova 容器下没有实现此接口--startCount');
            throw Error('uppedometerEngine is not defined!!!');
        };
        UppedometernClass.prototype.getSteps = function () {
            toast('cordova 容器下没有实现此接口--getSteps');
            throw Error('uppedometerEngine is not defined!!!');
        };
        UppedometernClass.prototype.stopCount = function () {
            toast('cordova 容器下没有实现此接口--stopCount');
            throw Error('uppedometerEngine is not defined!!!');
        };
        return UppedometernClass;
    }());

    var UpFNScannerClass = /** @class */ (function () {
        function UpFNScannerClass() {
            this.upFNScannerEngine = null;
        }
        UpFNScannerClass.prototype.setUpFNScannerEngine = function (upFNScannerEngine) {
            this.upFNScannerEngine = upFNScannerEngine;
        };
        UpFNScannerClass.prototype.open = function () {
            toast('cordova 容器下没有实现此接口--open');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.openScanner = function () {
            toast('cordova 容器下没有实现此接口--openScanner');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.openView = function () {
            toast('cordova 容器下没有实现此接口--openView');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.setFrame = function () {
            toast('cordova 容器下没有实现此接口--setFrame');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.closeView = function () {
            toast('cordova 容器下没有实现此接口--closeView');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.decodeImg = function () {
            toast('cordova 容器下没有实现此接口--decodeImg');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.encodeImg = function () {
            toast('cordova 容器下没有实现此接口--encodeImg');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.switchLight = function () {
            toast('cordova 容器下没有实现此接口--switchLight');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.onResume = function () {
            toast('cordova 容器下没有实现此接口--onResume');
            throw Error('uplocationEngine is not defined!!!');
        };
        UpFNScannerClass.prototype.onPause = function () {
            toast('cordova 容器下没有实现此接口--onPause');
            throw Error('uplocationEngine is not defined!!!');
        };
        return UpFNScannerClass;
    }());

    var UpbleClass = /** @class */ (function () {
        function UpbleClass() {
            this.upbleEngine = null;
        }
        UpbleClass.prototype.setUpbleEngine = function (upbleEngine) {
            this.upbleEngine = upbleEngine;
        };
        UpbleClass.prototype.initManager = function () {
            toast('cordova 容器下没有实现此接口--initManager');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.scan = function () {
            toast('cordova 容器下没有实现此接口--scan');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.getPeripheral = function () {
            toast('cordova 容器下没有实现此接口--getPeripheral');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.getPeripheralRssi = function () {
            toast('cordova 容器下没有实现此接口--getPeripheralRssi');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.isScanning = function () {
            toast('cordova 容器下没有实现此接口--isScanning');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.stopScan = function () {
            toast('cordova 容器下没有实现此接口--stopScan');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.connect = function () {
            toast('cordova 容器下没有实现此接口--connect');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.disconnect = function () {
            toast('cordova 容器下没有实现此接口--disconnect');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.isConnected = function () {
            toast('cordova 容器下没有实现此接口--isConnected');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.retrievePeripheral = function () {
            toast('cordova 容器下没有实现此接口--retrievePeripheral');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.retrieveConnectedPeripheral = function () {
            toast('cordova 容器下没有实现此接口--retrieveConnectedPeripheral');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.discoverService = function () {
            toast('cordova 容器下没有实现此接口--discoverService');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.discoverCharacteristics = function () {
            toast('cordova 容器下没有实现此接口--discoverCharacteristics');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.discoverDescriptorsForCharacteristic = function () {
            toast('cordova 容器下没有实现此接口--discoverDescriptorsForCharacteristic');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.setNotify = function () {
            toast('cordova 容器下没有实现此接口--setNotify');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.stopNotify = function () {
            toast('cordova 容器下没有实现此接口--stopNotify');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.readValueForCharacteristic = function () {
            toast('cordova 容器下没有实现此接口--readValueForCharacteristic');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.readValueForDescriptor = function () {
            toast('cordova 容器下没有实现此接口--readValueForDescriptor');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.writeValueForCharacteristic = function () {
            toast('cordova 容器下没有实现此接口--writeValueForCharacteristic');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.writeValueForDescriptor = function () {
            toast('cordova 容器下没有实现此接口--writeValueForDescriptor');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.connectPeripherals = function () {
            toast('cordova 容器下没有实现此接口--connectPeripherals');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.setSimpleNotify = function () {
            toast('cordova 容器下没有实现此接口--setSimpleNotify');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.getAllSimpleNotifyData = function () {
            toast('cordova 容器下没有实现此接口--getAllSimpleNotifyData');
            throw Error('upbleEngine is not defined!!!');
        };
        UpbleClass.prototype.clearAllSimpleNotifyData = function () {
            toast('cordova 容器下没有实现此接口--clearAllSimpleNotifyData');
            throw Error('upbleEngine is not defined!!!');
        };
        return UpbleClass;
    }());

    var UpImageClass = /** @class */ (function () {
        function UpImageClass() {
            this.upImageEngine = null;
        }
        UpImageClass.prototype.setUpImageEngine = function (upImageEngine) {
            this.upImageEngine = upImageEngine;
        };
        UpImageClass.prototype.imagePicker = function () {
            // toast('cordova 容器下没有实现此接口--imagePicker');
            // throw Error('upImageEngine is not defined!!!');
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upImageEngine) {
                    self.upImageEngine.imagePicker(function (e) {
                        // console.log('getPictureRealPath:0',e);
                        if (e.startsWith('content')) {
                            self.upImageEngine.getPictureRealPath(function (ret) {
                                // console.log('getPictureRealPath:1',ret);
                                if (ret.retCode === '000000') {
                                    resolve({
                                        retCode: '000000',
                                        retInfo: '操作成功',
                                        retData: {
                                            eventType: '',
                                            originalPath: '',
                                            assetPath: ret.retData,
                                            list: [],
                                        },
                                    });
                                }
                                else {
                                    reject({
                                        retCode: '900000',
                                        retInfo: 'imagePicker Failed!!!' + ret.retInfo,
                                        retData: '',
                                    });
                                }
                            }, function (ret) {
                                reject(ret);
                            }, e);
                        }
                        else {
                            // console.log('getPictureRealPath:2',e);
                            resolve({
                                retCode: '000000',
                                retInfo: '操作成功',
                                retData: {
                                    eventType: '',
                                    originalPath: '',
                                    assetPath: e,
                                    list: [],
                                },
                            });
                        }
                    }, function (e) {
                        // console.log('getPictureRealPath:3',e);
                        reject({
                            retCode: '900000',
                            retInfo: 'imagePicker Failed!!!' + e,
                            retData: '',
                        });
                    }, {
                        quality: 50,
                        sourceType: 2,
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'upImageEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        UpImageClass.prototype.closePicker = function () {
            toast('cordova 容器下没有实现此接口--closePicker');
            throw Error('upImageEngine is not defined!!!');
        };
        UpImageClass.prototype.transPath = function () {
            toast('cordova 容器下没有实现此接口--transPath');
            throw Error('upImageEngine is not defined!!!');
        };
        UpImageClass.prototype.openCamera = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upImageEngine) {
                    self.upImageEngine.imagePicker(function (e) {
                        resolve({
                            retCode: '000000',
                            retInfo: '操作成功',
                            retData: {
                                data: params.destinationType !== 2 ? e : null,
                                base64Data: params.destinationType === 2 ? e : null,
                                duration: 0,
                            },
                        });
                    }, function (e) {
                        reject({
                            retCode: '900000',
                            retInfo: 'imagePicker Failed!!!' + e,
                            retData: '',
                        });
                    }, {
                        quality: (params.quality || params.quality === 0) ? params.quality : 50,
                        sourceType: 1,
                        saveToPhotoAlbum: !!params.saveToPhotoAlbum,
                        destinationType: params.destinationType === 1 ? 1 : 0,
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'upImageEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        return UpImageClass;
    }());

    var AudioRecorderClass = /** @class */ (function () {
        function AudioRecorderClass() {
            this.audioRecorderEngine = null;
        }
        AudioRecorderClass.prototype.setaudioRecorderEngineEngine = function (audioRecorderEngine) {
            this.audioRecorderEngine = audioRecorderEngine;
        };
        AudioRecorderClass.prototype.startRecord = function () {
            toast('cordova 容器下没有实现此接口--startRecord');
            throw Error('audioRecorderEngine is not defined!!!');
        };
        AudioRecorderClass.prototype.stopRecord = function () {
            toast('cordova 容器下没有实现此接口--stopRecord');
            throw Error('audioRecorderEngine is not defined!!!');
        };
        AudioRecorderClass.prototype.getAttr = function () {
            toast('cordova 容器下没有实现此接口--getAttr');
            throw Error('audioRecorderEngine is not defined!!!');
        };
        return AudioRecorderClass;
    }());

    // 每次重绘都要去检查pageTrackObj，
    // 如果某个pageName的所有observeUrl的状态都为resolve,则认为该pageName的screen_end触发
    function mutationObserverCallback() {
        var observeUrls = this.pageTrackObj.observeUrls || [];
        var pageComplete = true;
        for (var j = 0; j < observeUrls.length; j++) {
            if (observeUrls[j] && observeUrls[j].status !== 'resolve') {
                pageComplete = false;
                break;
            }
        }
        if (pageComplete && this.upTraceEngine) {
            // 打点screen_end
            this.statPageEventData({
                pageName: this.pageTrackObj.pageName,
                tagName: 'screen_end',
                timeStamp: +(new Date()),
            });
            // 打过点之后就把该pageName 移除掉
            this.pageTrackObj = {};
            this.domObserver.disconnect();
            this.domObserver = null;
        }
    }

    var UpTraceClass = /** @class */ (function () {
        function UpTraceClass() {
            this.upTraceEngine = null;
            // 保留页面打点统计数据
            this.pageTrackObj = {};
            // 私有属性，保留domObserver
            this.domObserver = null;
        }
        UpTraceClass.prototype.setUpTraceEngine = function (upTraceEngine) {
            this.upTraceEngine = upTraceEngine;
        };
        UpTraceClass.prototype.reportSelfPageChange = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upTraceEngine) {
                    self.upTraceEngine.reportSelfPageChange(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, param);
                }
                else {
                    reject(resultAdapter({ retInfo: 'upTraceEngine is not defined!!!' }));
                }
            });
        };
        UpTraceClass.prototype.reportPageClickEvent = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upTraceEngine) {
                    self.upTraceEngine.reportPageClickEvent(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, param);
                }
                else {
                    reject(resultAdapter({ retInfo: 'upTraceEngine is not defined!!!' }));
                }
            });
        };
        UpTraceClass.prototype.statPageEventData = function (param) {
            var self = this;
            if (param && !param.pageName) {
                param.pageName = this.pageTrackObj.pageName || window.location.href;
            }
            if (typeof window.consoleLog === 'function') {
                window.consoleLog('打点信息--', param);
            }
            return new Promise(function (resolve, reject) {
                if (self.upTraceEngine && self.upTraceEngine.statPageEventData) {
                    self.upTraceEngine.statPageEventData(function (ret) { return resolve(resultAdapter(ret)); }, function (err) { return reject(resultAdapter(err)); }, param.pageName, param.tagName, param.timeStamp);
                }
                else {
                    reject(resultAdapter({ retInfo: 'upTraceEngine is not defined!!!' }));
                }
            });
        };
        UpTraceClass.prototype.initObserveRequest = function (param) {
            // 打screen_start点
            this.statPageEventData({
                pageName: param.pageName,
                tagName: 'screen_start',
                timeStamp: +(new Date()),
            }).catch(function () { return null; });
            // 如果domObserver已经存在，则先取消观察
            if (this.domObserver) {
                this.domObserver.disconnect();
            }
            if (param && param.pageName && param.observeUrls && param.observeUrls.length) {
                this.pageTrackObj = param;
                this.pageTrackObj.observeUrls = param.observeUrls.map(function (item) { return (__assign({}, item, { status: 'pending' })); });
                // 添加domObserver
                this.domObserver = new MutationObserver(mutationObserverCallback.bind(this));
                this.domObserver.observe(param.observeDom || document.querySelector('body'), {
                    childList: true,
                    subtree: true,
                });
            }
        };
        return UpTraceClass;
    }());

    function getDataSet(elemArr) {
        var _loop_1 = function (i) {
            if (elemArr[i] && elemArr[i].dataset && elemArr[i].dataset.uplusId) {
                var data_1 = {};
                Object.keys(elemArr[i].dataset).forEach(function (item) {
                    if (String(item).toLowerCase().startsWith('uplus')
                        && String(item).toLowerCase() !== 'uplusid' && elemArr[i].dataset[item]) {
                        data_1[item] = elemArr[i].dataset[item];
                    }
                });
                return { value: { id: elemArr[i].dataset.uplusId, data: data_1 } };
            }
        };
        for (var i = 0; i < elemArr.length; i++) {
            var state_1 = _loop_1(i);
            if (typeof state_1 === "object")
                return state_1.value;
        }
        return null;
    }

    var CordovaClass = /** @class */ (function () {
        function CordovaClass() {
            this.deviceReady = false;
            this.logicEngineModule = new LogicEngineClass();
            this.userModule = new UserClass();
            this.updeviceModule = new UpdeviceClass();
            this.vdnModule = new VdnClass();
            this.umshareModule = new UmshareClass();
            this.uplocationModule = new UplocationClass();
            this.httpModule = new HttpClass(this);
            this.upResourceModule = new UpResourceClass();
            this.upNetworkModule = new UpNetworkClass();
            this.upFsModule = new UpFsClass();
            this.upAudioModule = new UpAudioClass();
            this.uppedometerModule = new UppedometernClass();
            this.upFNScannerModule = new UpFNScannerClass();
            this.upbleModule = new UpbleClass();
            this.upImageModule = new UpImageClass();
            this.upAudioRecorderModule = new AudioRecorderClass();
            this.upTraceModule = new UpTraceClass();
            this.injectFlag = false;
        }
        CordovaClass.prototype.initDeviceReady = function () {
            var self = this;
            return new Promise(function (resolve) {
                if (self.deviceReady) {
                    resolve();
                }
                else {
                    if (!self.injectFlag) {
                        self.injectCodovaJs();
                        self.injectFlag = true;
                    }
                    document.addEventListener('deviceready', function () {
                        self.deviceReady = true;
                        self.logicEngineModule.setLogicEngine(window.cordova.require('uplus-plugin-logicengine.logicengine'));
                        self.userModule.setUserEngine(window.cordova.require('uplus-plugin-user.upuser'));
                        self.updeviceModule.setDeviceEngine(window.cordova.require('uphybrid-plugin-device.updevice'));
                        try {
                            self.vdnModule.setVdnEngine(window.cordova.require('uplus-plugin-vdn.upvdn'));
                        }
                        catch (error) {
                            self.vdnModule.setVdnEngine(null);
                        }
                        try {
                            self.upImageModule.setUpImageEngine(window.cordova.require('uplus-plugin-camera.upcamera'));
                        }
                        catch (error) {
                            self.upImageModule.setUpImageEngine(null);
                        }
                        try {
                            self.umshareModule.setUmshareEngine(window.cordova.require('uplus-plugin-share.upshare'));
                        }
                        catch (error) {
                            self.umshareModule.setUmshareEngine(null);
                        }
                        // self.uplocationModule.setUplocationEngine(window.api.require('UpLocationModule'));
                        self.httpModule.setHttpEngine(window.cordova.require('cordova-plugin-http.CordovaHttpPlugin'));
                        // upTraceModule 模块是在APP5.5之后才支持
                        try {
                            self.upTraceModule.setUpTraceEngine(window.cordova.require('uplus-plugin-trace.uptrace'));
                        }
                        catch (error) {
                            self.upTraceModule.setUpTraceEngine(null);
                        }
                        // 初始化页面点击事件监听
                        self._initUplusTrace();
                        resolve();
                    }, false);
                }
            });
        };
        // 注入cordova.js
        // uhome.haier.net:7900/saasapp/cordova/cordova.js 这个资源并不存在，
        // native端容器检测到这个资源请求时，会以APP端的本地资源来替换响应。
        CordovaClass.prototype.injectCodovaJs = function () {
            var head = document.getElementsByTagName('head')[0];
            var script = document.createElement('script');
            // 判断协议
            if (window && window.location && window.location.protocol === 'https:') {
                script.src = 'https://uhome.haier.net:7900/saasapp/cordova/cordova.js';
            }
            else {
                script.src = 'http://uhome.haier.net:7900/saasapp/cordova/cordova.js';
            }
            script.type = 'text/javascript';
            head.appendChild(script);
        };
        CordovaClass.prototype.closeH5ContainerView = function () {
            return new Promise(function (resolve, reject) {
                if (window.cordova.require('uphybrid-plugin-core.upcore')) {
                    window.cordova.require('uphybrid-plugin-core.upcore').closeH5ContainerView(function (data) {
                        return resolve(resultAdapter(data));
                    }, function (data) { return reject(resultAdapter(data)); });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        CordovaClass.prototype.getUrlParams = function () {
            return new Promise(function (resolve) {
                resolve({
                    retCode: '000000',
                    retInfo: '',
                    retData: {
                        hash: window.location.hash || '',
                        search: window.location.search || '',
                    },
                });
            });
        };
        CordovaClass.prototype.getStatusBarHeight = function () {
            return new Promise(function (resolve, reject) {
                if (window.cordova.require('uphybrid-plugin-core.upcore')) {
                    window.cordova.require('uphybrid-plugin-core.upcore').getStatusBarHeight(function (data) {
                        return resolve(resultAdapter(data));
                    }, function (data) { return reject(resultAdapter(data)); });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        CordovaClass.prototype.addPauseEventListener = function (eventListener) {
            document.addEventListener('pause', eventListener, false);
        };
        CordovaClass.prototype.addResumeEventListener = function (eventListener) {
            document.addEventListener('resume', eventListener, false);
        };
        CordovaClass.prototype.getDeviceInfoByMac = function (params) {
            var _this = this;
            var self = this;
            return new Promise(function (resolve, reject) { return __awaiter(_this, void 0, void 0, function () {
                var requestBody, userInfo, timestamp, signCode, response, error_1;
                return __generator(this, function (_a) {
                    switch (_a.label) {
                        case 0:
                            _a.trys.push([0, 4, , 5]);
                            if (!params || !params.deviceId) {
                                throw Error('deviceId 不允许为空');
                            }
                            requestBody = {
                                deviceId: params.deviceId || '',
                            };
                            return [4 /*yield*/, self.userModule.getUserInfo()];
                        case 1:
                            userInfo = _a.sent();
                            timestamp = (+new Date()).toString();
                            return [4 /*yield*/, self.userModule.getSignCode({
                                    data: JSON.stringify(requestBody),
                                    timestamp: timestamp,
                                })];
                        case 2:
                            signCode = _a.sent();
                            // http 请求
                            // 优先用 签名返回的 body
                            if (signCode && signCode.retData && signCode.retData.body) {
                                try {
                                    requestBody = JSON.parse(signCode.retData.body);
                                }
                                catch (_b) { }
                            }
                            return [4 /*yield*/, self.httpModule.post({
                                    url: 'http://uhome.haier.net:7500/emuplus/device/v3.0/detail',
                                    data: requestBody,
                                    headers: {
                                        // app信息
                                        appId: userInfo.retData.appId || '',
                                        appKey: userInfo.retData.appKey || '',
                                        appName: userInfo.retData.appName || '',
                                        appVersion: userInfo.retData.appVersion || '',
                                        clientId: userInfo.retData.clientId || '',
                                        // 登录信息
                                        accessToken: userInfo.retData.accessToken || '',
                                        userId: userInfo.retData.userId || '',
                                        // 签名信息
                                        sign: signCode.retData.sign || '',
                                        timestamp: timestamp,
                                        'Content-Type': 'application/json;charset=utf-8',
                                    },
                                })];
                        case 3:
                            response = _a.sent();
                            // 返回值
                            resolve(response);
                            return [3 /*break*/, 5];
                        case 4:
                            error_1 = _a.sent();
                            reject(error_1);
                            return [3 /*break*/, 5];
                        case 5: return [2 /*return*/];
                    }
                });
            }); });
        };
        CordovaClass.prototype._initUplusTrace = function () {
            var self = this;
            document.removeEventListener('click', this._uplusTraceHandler.bind(self));
            document.addEventListener('click', this._uplusTraceHandler.bind(self));
        };
        CordovaClass.prototype._uplusTraceHandler = function (event) {
            var elms = event.path || event.composedPath();
            var dataset = getDataSet(elms);
            if (dataset) {
                this.upTraceModule.reportPageClickEvent({ actionCode: dataset.id, extentInfo: dataset.data });
                if (typeof window.consoleLog === 'function') {
                    window.consoleLog(dataset.id);
                }
            }
        };
        return CordovaClass;
    }());

    var LogicEngineClass$1 = /** @class */ (function () {
        function LogicEngineClass(uplusApiInstance) {
            this.logicEngine = null;
            this.storeEngine = null;
            this.deviceMac = '';
            this.deviceChangeListener = null;
            this.uplusApiInstance = uplusApiInstance;
        }
        LogicEngineClass.prototype.setLogicEngine = function (logicEngine) {
            this.logicEngine = logicEngine;
        };
        LogicEngineClass.prototype.setStoreEngine = function (storeEngine) {
            this.storeEngine = storeEngine;
        };
        LogicEngineClass.prototype.setDeviceMac = function (deviceMac) {
            this.deviceMac = deviceMac;
        };
        LogicEngineClass.prototype.setDeviceChangeListener = function (deviceChangeListener) {
            this.deviceChangeListener = deviceChangeListener;
        };
        LogicEngineClass.prototype.getBaseInfoPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    iteratorApiCloud(self.logicEngine.getBaseInfo.bind(self.logicEngine), { 'deviceId': mac }, resolve, reject);
                }
            });
        };
        LogicEngineClass.prototype.getInitialAttributeListPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.logicEngine.getInitialAttributeList({ 'deviceId': mac }, (ret: any, err: any) => {
                    //   if (ret && resolve) {
                    //     resolve(resultAdapter(ret));
                    //   } else if (reject) {
                    //     reject(resultAdapter(err));
                    //   }
                    // });
                    iteratorApiCloud(self.logicEngine.getInitialAttributeList.bind(self.logicEngine), { 'deviceId': mac }, resolve, reject);
                }
            });
        };
        LogicEngineClass.prototype.getAttributeListPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.logicEngine.getAttributeList({ 'deviceId': mac }, (ret: any, err: any) => {
                    //   if (ret && resolve) {
                    //     resolve(resultAdapter(ret));
                    //   } else if (reject) {
                    //     reject(resultAdapter(err));
                    //   }
                    // });
                    iteratorApiCloud(self.logicEngine.getAttributeList.bind(self.logicEngine), { 'deviceId': mac }, resolve, reject);
                }
            });
        };
        LogicEngineClass.prototype.getAttributeByNamePromise = function (mac, name) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.getAttributeByName({ 'deviceId': mac, name: name }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        LogicEngineClass.prototype.getCautionsPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.logicEngine.getCautionList({ 'deviceId': mac }, (ret: any, err: any) => {
                    //   if (ret && resolve) {
                    //     resolve(resultAdapter(ret));
                    //   } else if (reject) {
                    //     reject(resultAdapter(err));
                    //   }
                    // });
                    iteratorApiCloud(self.logicEngine.getCautionList.bind(self.logicEngine), { 'deviceId': mac }, resolve, reject);
                }
            });
        };
        LogicEngineClass.prototype.attachPromise = function (mac, deviceChangeListener) {
            var _this = this;
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    // self.deviceChangeListener = deviceChangeListener;
                    // self.deviceMac = mac;
                    self.setDeviceMac(mac);
                    self.setDeviceChangeListener(deviceChangeListener);
                    // self.logicEngine.attach({ 'deviceId': mac }, (ret: any, err: any) => {
                    //   if (ret && resolve) {
                    //     resolve(resultAdapter(ret));
                    //   } else if (reject) {
                    //     reject(resultAdapter(err));
                    //   }
                    // });
                    iteratorApiCloud(self.logicEngine.attach.bind(self.logicEngine), { 'deviceId': mac }, resolve, reject);
                    window.onUplusReportLogicConnection = function (deviceId, baseInfo) {
                        if (typeof deviceChangeListener === 'function') {
                            deviceChangeListener(deviceId, baseInfo);
                        }
                    };
                    window.onUplusReportLogicAttributes = function (deviceId, baseInfo, attributes) {
                        if (typeof deviceChangeListener === 'function') {
                            deviceChangeListener(deviceId, baseInfo, attributes);
                        }
                    };
                    window.onUplusReportLogicCaution = function (deviceId, baseInfo, attributes, cautions) {
                        if (typeof deviceChangeListener === 'function') {
                            deviceChangeListener(deviceId, baseInfo, attributes, cautions);
                        }
                    };
                    // if (window.api) {
                    //   window.api.addEventListener({
                    //     name: 'resume',
                    //   }, async () => {
                    //     const baseInfo: any = await self.getBaseInfoPromise(mac);
                    //     const attributes: any = await self.getAttributeListPromise(mac);
                    //     const cautions: any = await self.getCautionsPromise(mac);
                    //     if (typeof deviceChangeListener === 'function') {
                    //       deviceChangeListener(mac, baseInfo.retData, attributes.retData, cautions.retData);
                    //     }
                    //     self.attachPromise(mac, deviceChangeListener);
                    //   });
                    // }
                    _this.uplusApiInstance.addResumeEventListener(function () { return __awaiter(_this, void 0, void 0, function () {
                        var baseInfo, attributes, cautions;
                        return __generator(this, function (_a) {
                            switch (_a.label) {
                                case 0: return [4 /*yield*/, self.getBaseInfoPromise(mac)];
                                case 1:
                                    baseInfo = _a.sent();
                                    return [4 /*yield*/, self.getAttributeListPromise(mac)];
                                case 2:
                                    attributes = _a.sent();
                                    return [4 /*yield*/, self.getCautionsPromise(mac)];
                                case 3:
                                    cautions = _a.sent();
                                    if (typeof deviceChangeListener === 'function') {
                                        deviceChangeListener(mac, baseInfo.retData, attributes.retData, cautions.retData);
                                    }
                                    self.attachPromise(mac, deviceChangeListener);
                                    return [2 /*return*/];
                            }
                        });
                    }); });
                }
            });
        };
        LogicEngineClass.prototype.detachPromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.detach({ 'deviceId': mac }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        LogicEngineClass.prototype.calculatePromise = function (mac, name, value, clearFlag) {
            if (clearFlag === void 0) { clearFlag = true; }
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof self.storeEngine === 'function') {
                    var command = { name: name, value: value };
                    self.storeEngine([command]);
                    resolve();
                    return;
                }
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.calculate({
                        'deviceId': mac,
                        'command': { 'name': name, 'value': "" + value },
                        'clear': clearFlag,
                    }, function (ret, err) {
                        if (ret) {
                            // 成功回调
                            resolve(resultAdapter(ret));
                            // 使用自定义的console方法
                            if (typeof window.consoleLog === 'function') {
                                window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u6210\u529F--name:" + name + ",value:" + value);
                            }
                        }
                        else {
                            // 失败回调
                            reject(resultAdapter(err));
                            // 使用自定义的console方法
                            if (typeof window.consoleLog === 'function') {
                                window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u5931\u8D25--name:" + name + ",value:" + value + ", error--", err);
                            }
                        }
                    });
                }
            });
        };
        LogicEngineClass.prototype.calculateAllPromise = function (mac, commands, clearFlag) {
            if (clearFlag === void 0) { clearFlag = true; }
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof self.storeEngine === 'function') {
                    self.storeEngine(commands);
                    resolve();
                    return;
                }
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.calculateAll({
                        'deviceId': mac,
                        'commands': commands,
                        'clear': clearFlag,
                    }, function (ret, err) {
                        if (ret) {
                            // 成功回调
                            resolve(resultAdapter(ret));
                            // 使用自定义的console方法
                            if (typeof window.consoleLog === 'function') {
                                window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u6210\u529F--" + JSON.stringify(commands));
                            }
                        }
                        else {
                            // 失败回调
                            reject(resultAdapter(err));
                            // 使用自定义的console方法
                            if (typeof window.consoleLog === 'function') {
                                window.consoleLog("\u8BA1\u7B97\u5C5E\u6027\u5931\u8D25--" + JSON.stringify(commands) + ", error--", err);
                            }
                        }
                    });
                }
            });
        };
        LogicEngineClass.prototype.operatePromise = function (mac) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.logicEngine) {
                    reject(resultAdapter({ retInfo: 'logicEngine is not defined!!!' }));
                }
                else {
                    self.logicEngine.operate({
                        'deviceId': mac,
                    }, function (ret, err) {
                        if (ret) {
                            // 成功回调
                            resolve(resultAdapter(ret));
                            // 使用自定义的console方法
                            if (typeof window.consoleLog === 'function') {
                                window.consoleLog("\u4E0B\u53D1\u5C5E\u6027\u6210\u529F");
                            }
                        }
                        else {
                            // 失败回调
                            reject(resultAdapter(err));
                            // 使用自定义的console方法
                            if (typeof window.consoleLog === 'function') {
                                window.consoleLog("\u4E0B\u53D1\u5C5E\u6027\u5931\u8D25--error--", err);
                            }
                        }
                    });
                }
            });
        };
        return LogicEngineClass;
    }());

    var UserClass$1 = /** @class */ (function () {
        function UserClass() {
            this.userEngine = null;
        }
        UserClass.prototype.setUserEngine = function (userEngine) {
            this.userEngine = userEngine;
        };
        UserClass.prototype.getLoginStatus = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.userEngine) {
                    self.userEngine.isLogin(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        UserClass.prototype.getUserInfo = function () {
            var _this = this;
            var self = this;
            return new Promise(function (resolve, reject) { return __awaiter(_this, void 0, void 0, function () {
                var appInfoData_1, _a;
                return __generator(this, function (_b) {
                    switch (_b.label) {
                        case 0:
                            if (!self.userEngine) return [3 /*break*/, 3];
                            appInfoData_1 = { retData: {} };
                            if (!(window.api && window.api.require('UpBaseModule'))) return [3 /*break*/, 2];
                            _a = appInfoData_1;
                            return [4 /*yield*/, new Promise(function (resolveInfo) {
                                    window.api.require('UpBaseModule').getAppInfo(function (retInfo) {
                                        resolveInfo(retInfo.retData);
                                    });
                                })];
                        case 1:
                            _a.retData = _b.sent();
                            _b.label = 2;
                        case 2:
                            self.userEngine.getUserInfo(function (ret, err) {
                                if (ret && resolve) {
                                    resolve(resultAdapter({
                                        retCode: ret.retCode,
                                        retInfo: ret.retInfo,
                                        retData: __assign({}, ret.retData, ret.retData.extras, appInfoData_1.retData),
                                    }));
                                }
                                else if (reject) {
                                    reject(resultAdapter(err));
                                }
                            });
                            return [3 /*break*/, 4];
                        case 3:
                            reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                            _b.label = 4;
                        case 4: return [2 /*return*/];
                    }
                });
            }); });
        };
        UserClass.prototype.getSignCode = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.userEngine) {
                    self.userEngine.getSignCode(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        UserClass.prototype.getCommonSignCode = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.userEngine) {
                    self.userEngine.getCommonSignCode(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        UserClass.prototype.getCommonSignCodeAndBody = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.userEngine) {
                    self.userEngine.getCommonSignCode(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        return UserClass;
    }());

    var UpdeviceClass$1 = /** @class */ (function () {
        function UpdeviceClass() {
            // 设备引擎
            this.deviceEngine = null;
        }
        // 设置设备引擎
        UpdeviceClass.prototype.setDeviceEngine = function (deviceEngine) {
            this.deviceEngine = deviceEngine;
        };
        UpdeviceClass.prototype.getDeviceList = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getDeviceList({
                        familyId: (data && data.familyId) ? data.familyId : null,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpdeviceClass.prototype.getDeviceInfoById = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getDeviceInfoById({ deviceId: data.deviceId }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpdeviceClass.prototype.getSubDevList = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getSubDevList({ deviceId: data.deviceId }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpdeviceClass.prototype.getSubDevInfoById = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.getSubDevInfoById({
                        deviceId: data.deviceId,
                        subDevId: data.subDevId,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpdeviceClass.prototype.executeOperation = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.executeOperation({
                        deviceId: data.deviceId,
                        subDevId: data.subDevId,
                        groupName: data.groupName,
                        command: data.command,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpdeviceClass.prototype.subscribeResource = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.subscribeResource({
                        deviceId: data.deviceId,
                        resName: data.resName,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpdeviceClass.prototype.unsubscribeResource = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.deviceEngine) {
                    reject(resultAdapter({ retInfo: 'deviceEngine is not defined!!!' }));
                }
                else {
                    self.deviceEngine.unsubscribeResource({
                        deviceId: data.deviceId,
                        resName: data.resName,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpdeviceClass.prototype.initListeners = function (data) {
            try {
                // 订阅
                this.deviceEngine.subscribeNewDeviceListChange();
                this.deviceEngine.subscribeNewDeviceChange({ deviceId: data.deviceId });
                this.deviceEngine.subscribeNewSubDevChange({ deviceId: data.deviceId });
            }
            catch (_a) { }
            // 注册监听器
            window.publishNewDeviceChange = function (mac, deviceInfo) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (mac && typeof data.deviceInfoListener === 'function') {
                    data.deviceInfoListener(mac, deviceInfo);
                }
            };
            window.publishNewDeviceListChange = function (deviceList) {
                if (deviceList === void 0) { deviceList = []; }
                if (typeof data.deviceListListener === 'function') {
                    data.deviceListListener(deviceList);
                }
            };
            window.publishNewSubDevChange = function (mac, deviceInfo) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (mac && typeof data.subDeviceInfoListener === 'function') {
                    data.subDeviceInfoListener(mac, deviceInfo);
                }
            };
            window.publishNewSubDevListChange = function (mac, deviceList) {
                if (deviceList === void 0) { deviceList = []; }
                if (typeof data.subDeviceListListener === 'function') {
                    data.subDeviceListListener(mac, deviceList);
                }
            };
            window.publishNewDeviceAlarm = function (mac, deviceInfo, alarmList) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (alarmList === void 0) { alarmList = []; }
                if (typeof data.subDeviceListListener === 'function') {
                    data.deviceAlarmListener(mac, deviceInfo, alarmList);
                }
            };
            window.publishNewSubDevAlarm = function (mac, deviceInfo, alarmList) {
                if (deviceInfo === void 0) { deviceInfo = {}; }
                if (alarmList === void 0) { alarmList = []; }
                if (typeof data.subDeviceListListener === 'function') {
                    data.subDeviceAlarmListener(mac, deviceInfo, alarmList);
                }
            };
            window.onUplusReportDeviceReceive = function (devicemac, resoureName, resoureData) {
                if (typeof data.onUplusReportDeviceReceive === 'function') {
                    data.onUplusReportDeviceReceive(devicemac, resoureName, resoureData);
                }
            };
        };
        return UpdeviceClass;
    }());

    var VdnClass$1 = /** @class */ (function () {
        function VdnClass() {
            this.vdnEngine = null;
        }
        VdnClass.prototype.setVdnEngine = function (vdnEngine) {
            this.vdnEngine = vdnEngine;
        };
        VdnClass.prototype.goToPage = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.vdnEngine) {
                    self.vdnEngine.goToPage(param, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'vdnEngine is not defined!!!' }));
                }
            });
        };
        VdnClass.prototype.goBack = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.vdnEngine) {
                    self.vdnEngine.goBack(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'vdnEngine is not defined!!!' }));
                }
            });
        };
        return VdnClass;
    }());

    var UmshareClass$1 = /** @class */ (function () {
        function UmshareClass() {
            this.umshareEngine = null;
        }
        UmshareClass.prototype.setUmshareEngine = function (umshareEngine) {
            this.umshareEngine = umshareEngine;
        };
        UmshareClass.prototype.showShareView = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.umshareEngine) {
                    self.umshareEngine.showShareView(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        return UmshareClass;
    }());

    var UplocationClass$1 = /** @class */ (function () {
        function UplocationClass() {
            this.uplocationEngine = null;
        }
        UplocationClass.prototype.setUplocationEngine = function (uplocationEngine) {
            this.uplocationEngine = uplocationEngine;
        };
        UplocationClass.prototype.getLocation = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof window.api.requestPermission === 'function' && self.uplocationEngine) {
                    // 申请权限
                    window.api.requestPermission({
                        list: ['location'],
                    }, function (res) {
                        if (res && res.list && res.list.length > 0 && res.list[0].granted) {
                            self.uplocationEngine.getLocation(function (ret, err) {
                                if (ret && resolve) {
                                    resolve(resultAdapter(ret));
                                }
                                else if (reject) {
                                    reject(resultAdapter(err));
                                }
                            });
                        }
                        else {
                            reject(resultAdapter({ retInfo: '获取定位权限失败！！！' }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'uplocationEngine is not defined!!!' }));
                }
            });
        };
        return UplocationClass;
    }());

    function adjustRetData$1(data, resolve, reject) {
        if (data) {
            var responseData = data;
            if (Object.prototype.toString.call(data) !== '[object Object]') {
                responseData = JSON.parse(data);
            }
            resolve(__assign({}, responseData, { retCode: responseData.retCode, retInfo: responseData.retInfo, retData: responseData.data }));
        }
        else {
            reject({
                retCode: '',
                retInfo: '未知错误',
                retData: data,
            });
        }
    }
    var HttpClass$1 = /** @class */ (function () {
        function HttpClass(uplusApiInstance) {
            this.httpEngine = null;
            this.uplusApiInstance = uplusApiInstance;
        }
        HttpClass.prototype.setHttpEngine = function (httpEngine) {
            this.httpEngine = httpEngine;
        };
        HttpClass.prototype.get = function (options) {
            var _this = this;
            // 打点开始
            this.statPageEventData({
                tagName: "net_" + options.url + "_start",
                timeStamp: +(new Date()),
            });
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.httpEngine) {
                    self.httpEngine({
                        url: options.url, method: 'get',
                        data: { body: JSON.stringify(options.data) }, headers: options.headers,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            // 打点结束
                            _this.statPageEventData({
                                tagName: "net_" + options.url + "_end",
                                timeStamp: +(new Date()),
                            });
                            // 更新track记录
                            _this.updateTrackUrlStatus(options.url, 'get');
                            adjustRetData$1(ret, resolve, reject);
                        }
                        else if (reject) {
                            reject(err);
                        }
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        HttpClass.prototype.post = function (options) {
            var _this = this;
            // 打点开始
            this.statPageEventData({
                tagName: "net_" + options.url + "_start",
                timeStamp: +(new Date()),
            });
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.httpEngine) {
                    self.httpEngine({
                        url: options.url, method: 'post',
                        data: { body: JSON.stringify(options.data) }, headers: options.headers,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            // 打点结束
                            _this.statPageEventData({
                                tagName: "net_" + options.url + "_end",
                                timeStamp: +(new Date()),
                            });
                            // 更新track记录
                            _this.updateTrackUrlStatus(options.url, 'post');
                            adjustRetData$1(ret, resolve, reject);
                        }
                        else if (reject) {
                            reject(err);
                        }
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        HttpClass.prototype.head = function (options) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.httpEngine) {
                    self.httpEngine({
                        url: options.url, method: 'head',
                        data: { body: JSON.stringify(options.data) }, headers: options.headers,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            // resolve(ret);
                            adjustRetData$1(ret, resolve, reject);
                        }
                        else if (reject) {
                            reject(err);
                        }
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        HttpClass.prototype.uploadFile = function (options) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.httpEngine) {
                    self.httpEngine({
                        url: options.url, method: 'post',
                        data: { values: __assign({}, options.data, { name: options.name }), files: { file: options.filePath } },
                        headers: options.headers,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            // resolve(ret);
                            adjustRetData$1(ret, resolve, reject);
                        }
                        else if (reject) {
                            reject(err);
                        }
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        HttpClass.prototype.downloadFile = function (options) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.httpEngine) {
                    window.api.download({
                        url: options.url,
                        savePath: options.filePath,
                    }, function (ret, err) {
                        if (ret && resolve) {
                            // resolve(ret);
                            adjustRetData$1(ret, resolve, reject);
                        }
                        else if (reject) {
                            reject(err);
                        }
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'httpEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        HttpClass.prototype.statPageEventData = function (params) {
            if (this.uplusApiInstance && this.uplusApiInstance.upTraceModule) {
                this.uplusApiInstance.upTraceModule.statPageEventData(params).catch(function () { return null; });
            }
        };
        HttpClass.prototype.updateTrackUrlStatus = function (url, method) {
            if (this.uplusApiInstance && this.uplusApiInstance.upTraceModule) {
                var observeUrls = this.uplusApiInstance.upTraceModule.pageTrackObj.observeUrls || [];
                for (var i = 0; i < observeUrls.length; i++) {
                    if (observeUrls[i] && observeUrls[i].url === url && observeUrls[i].method === method) {
                        observeUrls[i].status = 'resolve';
                        break;
                    }
                }
            }
        };
        return HttpClass;
    }());

    var UpNetworkClass$1 = /** @class */ (function () {
        function UpNetworkClass() {
        }
        UpNetworkClass.prototype.setUpnetworkEngine = function (upnetworkEngine) {
            this.upnetworkEngine = upnetworkEngine;
        };
        UpNetworkClass.prototype.isOnline = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upnetworkEngine) {
                    reject(resultAdapter({ retInfo: 'upnetworkEngine is not defined!!!' }));
                }
                else {
                    self.upnetworkEngine.isOnline(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpNetworkClass.prototype.getNetworkInfo = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upnetworkEngine) {
                    reject(resultAdapter({ retInfo: 'upnetworkEngine is not defined!!!' }));
                }
                else {
                    self.upnetworkEngine.getNetworkInfo(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpNetworkClass.prototype.getWifiMacAddress = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upnetworkEngine) {
                    reject(resultAdapter({ retInfo: 'upnetworkEngine is not defined!!!' }));
                }
                else {
                    self.upnetworkEngine.getWifiMacAddress(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        return UpNetworkClass;
    }());

    var UpResourceClass$1 = /** @class */ (function () {
        function UpResourceClass() {
        }
        UpResourceClass.prototype.setUpresourceEngine = function (upresourceEngine) {
            this.upresourceEngine = upresourceEngine;
        };
        UpResourceClass.prototype.update = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.update(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.updateDeviceConfig = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.updateDeviceConfig(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.getPresentInfoList = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.getPresentInfoList(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.getLatestInfoList = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.getLatestInfoList(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.getActiveInfoList = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.getActiveInfoList(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.install = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.install(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.uninstall = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.uninstall(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.upgrade = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.upgrade(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.cancelDownload = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.cancelDownload(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.resumeDownload = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.resumeDownload(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        UpResourceClass.prototype.cancel = function (data) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upresourceEngine) {
                    reject(resultAdapter({ retInfo: 'upresourceEngine is not defined!!!' }));
                }
                else {
                    self.upresourceEngine.cancel(data, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
            });
        };
        return UpResourceClass;
    }());

    var UpFsClass$1 = /** @class */ (function () {
        function UpFsClass() {
        }
        UpFsClass.prototype.setUpfsEngine = function (upfsEngine) {
            this.upfsEngine = upfsEngine;
        };
        UpFsClass.prototype.createDir = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.createDir(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.rmdir = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.rmdir(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.createFile = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.createFile(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.remove = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.remove(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.copyTo = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.copyTo(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.moveTo = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.moveTo(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.rename = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.rename(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.readDir = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.readDir(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.open = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.open(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.read = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.read(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.readUp = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.readUp(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.readDown = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.readDown(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.write = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.write(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.close = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.close(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.exist = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.exist(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.getAttribute = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.getAttribute(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.readByLength = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.readByLength(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.writeByLength = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.writeByLength(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.getMD5 = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upfsEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upfsEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upfsEngine.getMD5(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpFsClass.prototype.createDirSync = function (params) {
            var ret = this.upfsEngine.createDirSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.rmdirSync = function (params) {
            var ret = this.upfsEngine.rmdirSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.createFileSync = function (params) {
            var ret = this.upfsEngine.createFileSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.removeSync = function (params) {
            var ret = this.upfsEngine.removeSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.copyToSync = function (params) {
            var ret = this.upfsEngine.copyToSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.moveToSync = function (params) {
            var ret = this.upfsEngine.moveToSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.renameSync = function (params) {
            var ret = this.upfsEngine.renameSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.readDirSync = function (params) {
            var ret = this.upfsEngine.readDirSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.openSync = function (params) {
            var ret = this.upfsEngine.openSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.readSync = function (params) {
            var ret = this.upfsEngine.readSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.readUpSync = function (params) {
            var ret = this.upfsEngine.readUpSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.readDownSync = function (params) {
            var ret = this.upfsEngine.readDownSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.writeSync = function (params) {
            var ret = this.upfsEngine.writeSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.closeSync = function (params) {
            var ret = this.upfsEngine.closeSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.existSync = function (params) {
            var ret = this.upfsEngine.existSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.getAttributeSync = function (params) {
            var ret = this.upfsEngine.getAttributeSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.readByLengthSync = function (params) {
            var ret = this.upfsEngine.readByLengthSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.writeByLengthSync = function (params) {
            var ret = this.upfsEngine.writeByLengthSync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        UpFsClass.prototype.getMD5Sync = function (params) {
            var ret = this.upfsEngine.getMD5Sync(params);
            return {
                retCode: '',
                retInfo: '',
                retData: ret,
            };
        };
        return UpFsClass;
    }());

    var UpAudioClass$1 = /** @class */ (function () {
        function UpAudioClass() {
        }
        UpAudioClass.prototype.setUpAudio = function (upAudio) {
            this.upAudio = upAudio;
        };
        UpAudioClass.prototype.initPlayer = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.initPlayer(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        UpAudioClass.prototype.play = function () {
            this.upAudio.play();
        };
        UpAudioClass.prototype.pause = function () {
            this.upAudio.pause();
        };
        UpAudioClass.prototype.stop = function () {
            this.upAudio.stop();
        };
        UpAudioClass.prototype.setVolume = function (params) {
            this.upAudio.setVolume(params);
        };
        UpAudioClass.prototype.getVolume = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.getVolume(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        UpAudioClass.prototype.setCurrent = function (params) {
            this.upAudio.setCurrent(params);
        };
        UpAudioClass.prototype.getCurrent = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.getCurrent(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        UpAudioClass.prototype.getState = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.getState(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        UpAudioClass.prototype.getBufferRatio = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.getBufferRatio(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        UpAudioClass.prototype.addEventListener = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.addEventListener(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        UpAudioClass.prototype.removeEventListener = function (params) {
            this.upAudio.removeEventListener(params);
        };
        UpAudioClass.prototype.clearCache = function () {
            this.upAudio.clearCache();
        };
        UpAudioClass.prototype.scanAudioLibrary = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.scanAudioLibrary(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        UpAudioClass.prototype.getAttr = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upAudio) {
                    self.upAudio.getAttr(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'umshareEngine is not defined!!!' }));
                }
            });
        };
        return UpAudioClass;
    }());

    var UppedometernClass$1 = /** @class */ (function () {
        function UppedometernClass() {
            this.uppedometerEngine = null;
        }
        UppedometernClass.prototype.setUppedometerEngine = function (uppedometerEngine) {
            this.uppedometerEngine = uppedometerEngine;
        };
        UppedometernClass.prototype.startCount = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.uppedometerEngine) {
                    reject(resultAdapter({ retInfo: 'uppedometerEngine is not defined!!!' }));
                }
                else {
                    self.uppedometerEngine.startCount(function (ret) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: ret }));
                        }
                    });
                }
            });
        };
        UppedometernClass.prototype.getSteps = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.uppedometerEngine) {
                    reject(resultAdapter({ retInfo: 'uppedometerEngine is not defined!!!' }));
                }
                else {
                    self.uppedometerEngine.getSteps(function (ret) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: ret }));
                        }
                    });
                }
            });
        };
        UppedometernClass.prototype.stopCount = function () {
            if (this.uppedometerEngine) {
                this.uppedometerEngine.stopCount();
            }
            else {
                throw Error('uppedometerEngine is not defined!!!');
            }
        };
        return UppedometernClass;
    }());

    var UpFNScannerClass$1 = /** @class */ (function () {
        function UpFNScannerClass() {
            this.upFNScannerEngine = null;
        }
        UpFNScannerClass.prototype.setUpFNScannerEngine = function (upFNScannerEngine) {
            this.upFNScannerEngine = upFNScannerEngine;
        };
        UpFNScannerClass.prototype.open = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upFNScannerEngine) {
                    reject(resultAdapter({ retInfo: 'upFNScannerEngine is not defined!!!' }));
                }
                else {
                    self.upFNScannerEngine.open(params, function (ret, err) {
                        if (ret && ret.eventType === 'success' && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (err && reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
            });
        };
        UpFNScannerClass.prototype.openScanner = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (window.api && typeof window.api.requestPermission === 'function') {
                    window.api.requestPermission({
                        list: ['camera', 'storage'],
                    }, function (res) {
                        if (res && res.list && res.list.length > 0 && res.list[0].granted) {
                            self.upFNScannerEngine.openScanner(params, function (ret, err) {
                                if (ret && ret.eventType === 'success' && resolve) {
                                    resolve(resultAdapter({ retData: ret }));
                                }
                                else if (err && reject) {
                                    reject(resultAdapter({ retData: err }));
                                }
                            });
                        }
                        else {
                            reject(resultAdapter({ retInfo: '获取权限失败！！！' }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'upFNScannerEngine is not defined!!!' }));
                }
            });
        };
        UpFNScannerClass.prototype.openView = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upFNScannerEngine) {
                    reject(resultAdapter({ retInfo: 'upFNScannerEngine is not defined!!!' }));
                }
                else {
                    self.upFNScannerEngine.openView(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
            });
        };
        UpFNScannerClass.prototype.setFrame = function (params) {
            if (!this.upFNScannerEngine) {
                throw Error('uplocationEngine is not defined!!!');
            }
            else {
                this.upFNScannerEngine.setFrame(params);
            }
        };
        UpFNScannerClass.prototype.closeView = function () {
            if (!this.upFNScannerEngine) {
                throw Error('uplocationEngine is not defined!!!');
            }
            else {
                this.upFNScannerEngine.closeView();
            }
        };
        UpFNScannerClass.prototype.decodeImg = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upFNScannerEngine) {
                    reject(resultAdapter({ retInfo: 'upFNScannerEngine is not defined!!!' }));
                }
                else {
                    self.upFNScannerEngine.decodeImg(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
            });
        };
        UpFNScannerClass.prototype.encodeImg = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upFNScannerEngine) {
                    reject(resultAdapter({ retInfo: 'upFNScannerEngine is not defined!!!' }));
                }
                else {
                    self.upFNScannerEngine.encodeImg(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
            });
        };
        UpFNScannerClass.prototype.switchLight = function (params) {
            if (!this.upFNScannerEngine) {
                throw Error('uplocationEngine is not defined!!!');
            }
            else {
                this.upFNScannerEngine.switchLight(params);
            }
        };
        UpFNScannerClass.prototype.onResume = function () {
            if (!this.upFNScannerEngine) {
                throw Error('uplocationEngine is not defined!!!');
            }
            else {
                this.upFNScannerEngine.onResume();
            }
        };
        UpFNScannerClass.prototype.onPause = function () {
            if (!this.upFNScannerEngine) {
                throw Error('uplocationEngine is not defined!!!');
            }
            else {
                this.upFNScannerEngine.onPause();
            }
        };
        return UpFNScannerClass;
    }());

    var UpbleClass$1 = /** @class */ (function () {
        function UpbleClass() {
            this.upbleEngine = null;
        }
        UpbleClass.prototype.setUpbleEngine = function (upbleEngine) {
            this.upbleEngine = upbleEngine;
        };
        UpbleClass.prototype.initManager = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.initManager(params, function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.scan = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.scan(params, function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.getPeripheral = function (listener) {
            var self = this;
            if (!self.upbleEngine) {
                throw Error('upbleEngine is not defined!!!');
            }
            else {
                self.upbleEngine.getPeripheral(function (ret, err) {
                    if (listener) {
                        listener({
                            retCode: '',
                            retInfo: '',
                            retData: ret || err,
                        });
                    }
                });
            }
        };
        UpbleClass.prototype.getPeripheralRssi = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.getPeripheralRssi(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.isScanning = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.isScanning(function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.stopScan = function () {
            if (!this.upbleEngine) {
                throw Error('upbleEngine is not defined!!!');
            }
            else {
                this.upbleEngine.stopScan();
            }
        };
        UpbleClass.prototype.connect = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.connect(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.disconnect = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.disconnect(params, function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.isConnected = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.isConnected(params, function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.retrievePeripheral = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.retrievePeripheral(params, function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.retrieveConnectedPeripheral = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.retrieveConnectedPeripheral(params, function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.discoverService = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.discoverService(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.discoverCharacteristics = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.discoverCharacteristics(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.discoverDescriptorsForCharacteristic = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.discoverDescriptorsForCharacteristic(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.setNotify = function (params) {
            var self = this;
            if (!self.upbleEngine) {
                throw Error('upbleEngine is not defined!!!');
            }
            else {
                self.upbleEngine.setNotify(params, function (ret, err) {
                    if (params.listener) {
                        params.listener({
                            retCode: '',
                            retInfo: '',
                            retData: ret || err,
                        });
                    }
                });
            }
        };
        UpbleClass.prototype.stopNotify = function () {
            if (!this.upbleEngine) {
                throw Error('upbleEngine is not defined!!!');
            }
            else {
                this.upbleEngine.stopNotify();
            }
        };
        UpbleClass.prototype.readValueForCharacteristic = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.readValueForCharacteristic(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.readValueForDescriptor = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.readValueForDescriptor(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.writeValueForCharacteristic = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.writeValueForCharacteristic(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.writeValueForDescriptor = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.writeValueForDescriptor(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.connectPeripherals = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.connectPeripherals(params, function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.setSimpleNotify = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.setSimpleNotify(params, function (ret, err) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: err,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.getAllSimpleNotifyData = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (!self.upbleEngine) {
                    reject({
                        retCode: '',
                        retInfo: 'upbleEngine is not defined!!!',
                        retData: '',
                    });
                }
                else {
                    self.upbleEngine.getAllSimpleNotifyData(function (ret) {
                        if (ret && resolve) {
                            resolve({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                        else if (reject) {
                            reject({
                                retCode: '',
                                retInfo: '',
                                retData: ret,
                            });
                        }
                    });
                }
            });
        };
        UpbleClass.prototype.clearAllSimpleNotifyData = function () {
            if (!this.upbleEngine) {
                throw Error('upbleEngine is not defined!!!');
            }
            else {
                this.upbleEngine.clearAllSimpleNotifyData();
            }
        };
        return UpbleClass;
    }());

    var UpImageClass$1 = /** @class */ (function () {
        function UpImageClass() {
            this.upImageEngine = null;
        }
        UpImageClass.prototype.setUpImageEngine = function (upImageEngine) {
            this.upImageEngine = upImageEngine;
        };
        UpImageClass.prototype.imagePicker = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof window.api.requestPermission === 'function' && self.upImageEngine) {
                    window.api.requestPermission({
                        list: ['camera', 'storage'],
                    }, function (res) {
                        if (res && res.list && res.list.length > 0 && res.list[0].granted) {
                            self.upImageEngine.imagePicker({
                                max: params.max || 9,
                                styles: {
                                    bg: '#000000',
                                    mark: {
                                        position: 'top_right',
                                        size: 20,
                                    },
                                    nav: {
                                        bg: '#000000',
                                        cancelColor: '#fff',
                                        cancelSize: 16,
                                        nextStepColor: '#7fff00',
                                        nextStepSize: 16,
                                    },
                                    thumbnail: {
                                        w: 100,
                                        h: 100,
                                    },
                                },
                                showCamera: false,
                                animation: true,
                            }, function (ret, err) {
                                if (ret && resolve) {
                                    resolve(resultAdapter({ retData: ret }));
                                }
                                else if (reject) {
                                    reject(resultAdapter({ retData: err }));
                                }
                            });
                        }
                        else {
                            reject(resultAdapter({ retInfo: '获取权限失败！！！' }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'upImageEngine is not defined!!!' }));
                }
            });
        };
        UpImageClass.prototype.closePicker = function () {
            this.upImageEngine.closePicker();
        };
        UpImageClass.prototype.transPath = function (params) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof window.api.requestPermission === 'function' && self.upImageEngine) {
                    window.api.requestPermission({
                        list: ['camera', 'storage'],
                    }, function (res) {
                        if (res && res.list && res.list.length > 0 && res.list[0].granted) {
                            self.upImageEngine.transPath(params, function (ret, err) {
                                if (ret && resolve) {
                                    resolve(resultAdapter({ retData: ret }));
                                }
                                else if (reject) {
                                    reject(resultAdapter({ retData: err }));
                                }
                            });
                        }
                        else {
                            reject(resultAdapter({ retInfo: '获取权限失败！！！' }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'upImageEngine is not defined!!!' }));
                }
            });
        };
        UpImageClass.prototype.openCamera = function (params) {
            return new Promise(function (resolve, reject) {
                if (window.api && typeof window.api.requestPermission === 'function') {
                    window.api.requestPermission({
                        list: ['camera', 'storage'],
                    }, function (res) {
                        if (res && res.list && res.list.length > 0 && res.list[0].granted) {
                            window.api.getPicture({
                                sourceType: 'camera',
                                encodingType: 'jpg',
                                mediaValue: 'pic',
                                destinationType: params.destinationType === 1 ? 'url' : 'base64',
                                allowEdit: true,
                                quality: (params.quality || params.quality === 0) ? params.quality : 50,
                                // targetWidth: 100,
                                // targetHeight: 100,
                                saveToPhotoAlbum: !!params.saveToPhotoAlbum,
                            }, function (ret, err) {
                                if (ret && resolve) {
                                    resolve(resultAdapter({ retData: ret }));
                                }
                                else if (reject) {
                                    reject(resultAdapter({ retData: err }));
                                }
                            });
                        }
                        else {
                            reject(resultAdapter({ retInfo: '获取权限失败！！！' }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'upImageEngine is not defined!!!' }));
                }
            });
        };
        return UpImageClass;
    }());

    var AudioRecorderClass$1 = /** @class */ (function () {
        function AudioRecorderClass() {
            this.audioRecorderEngine = null;
        }
        AudioRecorderClass.prototype.setaudioRecorderEngineEngine = function (audioRecorderEngine) {
            this.audioRecorderEngine = audioRecorderEngine;
        };
        AudioRecorderClass.prototype.startRecord = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (typeof window.api.requestPermission === 'function' && self.audioRecorderEngine) {
                    // 申请权限
                    window.api.requestPermission({
                        list: ['microphone', 'storage'],
                    }, function (res) {
                        if (res && res.list && res.list.length > 0 && res.list[0].granted) {
                            self.audioRecorderEngine.startRecord(__assign({}, param, { format: 'aac' }), function (ret, err) {
                                if (ret && resolve) {
                                    resolve(resultAdapter({ retData: ret }));
                                }
                                else if (reject) {
                                    reject(resultAdapter({ retData: err }));
                                }
                            });
                        }
                        else {
                            reject(resultAdapter({ retInfo: '获取录音权限失败！！！' }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'audioRecorderEngine is not defined!!!' }));
                }
            });
        };
        AudioRecorderClass.prototype.stopRecord = function () {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.audioRecorderEngine) {
                    self.audioRecorderEngine.stopRecord(function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'audioRecorderEngine is not defined!!!' }));
                }
            });
        };
        AudioRecorderClass.prototype.getAttr = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.audioRecorderEngine) {
                    self.audioRecorderEngine.getAttr(param, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter({ retData: ret }));
                        }
                        else if (reject) {
                            reject(resultAdapter({ retData: err }));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'audioRecorderEngine is not defined!!!' }));
                }
            });
        };
        return AudioRecorderClass;
    }());

    var UpTraceClass$1 = /** @class */ (function () {
        function UpTraceClass() {
            this.upTraceEngine = null;
            // 保留页面打点统计数据
            this.pageTrackObj = {};
            // 私有属性，保留domObserver
            this.domObserver = null;
        }
        UpTraceClass.prototype.setUpTraceEngine = function (upTraceEngine) {
            this.upTraceEngine = upTraceEngine;
        };
        UpTraceClass.prototype.reportSelfPageChange = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upTraceEngine) {
                    self.upTraceEngine.reportSelfPageChange(param, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'upTraceEngine is not defined!!!' }));
                }
            });
        };
        UpTraceClass.prototype.reportPageClickEvent = function (param) {
            var self = this;
            return new Promise(function (resolve, reject) {
                if (self.upTraceEngine) {
                    self.upTraceEngine.reportPageClickEvent(param, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'upTraceEngine is not defined!!!' }));
                }
            });
        };
        UpTraceClass.prototype.statPageEventData = function (param) {
            var self = this;
            if (param && !param.pageName) {
                param.pageName = this.pageTrackObj.pageName || window.location.href;
            }
            if (typeof window.consoleLog === 'function') {
                window.consoleLog('打点信息--', param);
            }
            return new Promise(function (resolve, reject) {
                if (self.upTraceEngine && self.upTraceEngine.statPageEventData) {
                    self.upTraceEngine.statPageEventData(param, function (ret, err) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(err));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'upTraceEngine is not defined!!!' }));
                }
            });
        };
        UpTraceClass.prototype.initObserveRequest = function (param) {
            // 打screen_start点
            this.statPageEventData({
                pageName: param.pageName,
                tagName: 'screen_start',
                timeStamp: +(new Date()),
            }).catch(function () { return null; });
            // 如果domObserver已经存在，则先取消观察
            if (this.domObserver) {
                this.domObserver.disconnect();
            }
            if (param && param.pageName && param.observeUrls && param.observeUrls.length) {
                this.pageTrackObj = param;
                this.pageTrackObj.observeUrls = param.observeUrls.map(function (item) { return (__assign({}, item, { status: 'pending' })); });
                // 添加domObserver
                this.domObserver = new MutationObserver(mutationObserverCallback.bind(this));
                this.domObserver.observe(param.observeDom || document.querySelector('body'), {
                    childList: true,
                    subtree: true,
                });
            }
        };
        return UpTraceClass;
    }());

    var ApicloudClass = /** @class */ (function () {
        function ApicloudClass() {
            this.deviceReady = false;
            this.logicEngineModule = new LogicEngineClass$1(this);
            this.userModule = new UserClass$1();
            this.updeviceModule = new UpdeviceClass$1();
            this.vdnModule = new VdnClass$1();
            this.umshareModule = new UmshareClass$1();
            this.uplocationModule = new UplocationClass$1();
            this.httpModule = new HttpClass$1(this);
            this.upResourceModule = new UpResourceClass$1();
            this.upNetworkModule = new UpNetworkClass$1();
            this.upFsModule = new UpFsClass$1();
            this.upAudioModule = new UpAudioClass$1();
            this.uppedometerModule = new UppedometernClass$1();
            this.upFNScannerModule = new UpFNScannerClass$1();
            this.upbleModule = new UpbleClass$1();
            this.upImageModule = new UpImageClass$1();
            this.upAudioRecorderModule = new AudioRecorderClass$1();
            this.upTraceModule = new UpTraceClass$1();
            // 私有属性，保留多次调用initDeviceReady的resolve
            this.eventStack = [];
            this.resumeEventStack = [];
            this.pauseEventStack = [];
        }
        // 初始化deviceReady
        ApicloudClass.prototype.initDeviceReady = function () {
            var self = this;
            return new Promise(function (resolve) {
                if (self.deviceReady) {
                    resolve();
                }
                else {
                    // 记录事件，加入队列
                    if (typeof resolve === 'function') {
                        self.eventStack.push(resolve);
                    }
                    window.apiready = function () {
                        self.deviceReady = true;
                        self.logicEngineModule.setLogicEngine(window.api.require('UpLogicEngineModule'));
                        self.userModule.setUserEngine(window.api.require('UpUserModule'));
                        self.updeviceModule.setDeviceEngine(window.api.require('UpDeviceModule'));
                        self.vdnModule.setVdnEngine(window.api.require('UpVdnModule'));
                        self.umshareModule.setUmshareEngine(window.api.require('UpUMShareModule'));
                        self.uplocationModule.setUplocationEngine(window.api.require('UpLocationModule'));
                        self.uppedometerModule.setUppedometerEngine(window.api.require('pedometer'));
                        self.upFNScannerModule.setUpFNScannerEngine(window.api.require('FNScanner'));
                        self.upbleModule.setUpbleEngine(window.api.require('ble'));
                        self.httpModule.setHttpEngine(window.api.ajax);
                        self.upResourceModule.setUpresourceEngine(window.api.require('UpResourceModule'));
                        self.upNetworkModule.setUpnetworkEngine(window.api.require('UpNetworkModule'));
                        self.upFsModule.setUpfsEngine(window.api.require('fs'));
                        self.upAudioModule.setUpAudio(window.api.require('audioPlayer'));
                        self.upImageModule.setUpImageEngine(window.api.require('UIAlbumBrowser'));
                        self.upAudioRecorderModule.setaudioRecorderEngineEngine(window.api.require('audioRecorder'));
                        self.upTraceModule.setUpTraceEngine(window.api.require('UpTraceModule'));
                        // 初始化页面点击事件监听
                        self._initUplusTrace();
                        // 遍历事件队列
                        self.eventStack.map(function (eventHandle) { return eventHandle(); });
                        // 清空事件队列
                        self.eventStack.splice(0, self.eventStack.length);
                        // resume && pause
                        if (window && /iPhone|mac|iPod|iPad/i.test(window.navigator.userAgent)) {
                            window.api.addEventListener({
                                name: 'viewappear',
                            }, function () { return self.resumeEventStack.map(function (eventHandle) {
                                try {
                                    eventHandle();
                                }
                                catch (_a) { }
                            }); });
                            window.api.addEventListener({
                                name: 'viewdisappear',
                            }, function () { return self.pauseEventStack.map(function (eventHandle) {
                                try {
                                    eventHandle();
                                }
                                catch (_a) { }
                            }); });
                        }
                        else {
                            window.api.addEventListener({
                                name: 'resume',
                            }, function () { return self.resumeEventStack.map(function (eventHandle) {
                                try {
                                    eventHandle();
                                }
                                catch (_a) { }
                            }); });
                            window.api.addEventListener({
                                name: 'pause',
                            }, function () { return self.pauseEventStack.map(function (eventHandle) {
                                try {
                                    eventHandle();
                                }
                                catch (_a) { }
                            }); });
                        }
                    };
                }
            });
        };
        // injectCodovaJs(): Promise<ResultInt> {
        //   return this.initDeviceReady();
        // }
        ApicloudClass.prototype.closeH5ContainerView = function () {
            return new Promise(function (resolve, reject) {
                if (window.api.require('UpBaseModule')) {
                    window.api.require('UpBaseModule').closeView();
                    resolve({
                        retCode: '',
                        retInfo: '',
                        retData: '',
                    });
                }
                else {
                    reject({
                        retCode: '',
                        retInfo: 'userEngine is not defined!!!',
                        retData: '',
                    });
                }
            });
        };
        ApicloudClass.prototype.getUrlParams = function () {
            return new Promise(function (resolve) {
                if (window.api && window.api.pageParam) {
                    var appParam = window.api.pageParam;
                    // try {
                    //   appParam = JSON.parse(window.api.appParam);
                    // } finally { }
                    if (appParam && appParam.uplusApicloudParameter) {
                        var uplusApicloudParameter = {};
                        try {
                            uplusApicloudParameter = JSON.parse(appParam.uplusApicloudParameter);
                            if (uplusApicloudParameter) {
                                resolve({
                                    retCode: '000000',
                                    retInfo: '',
                                    retData: {
                                        // hash: `#${uplusApicloudParameter.hash}` || '',
                                        // search: `?${uplusApicloudParameter.search}` || '',
                                        hash: uplusApicloudParameter.hash ? "#" + uplusApicloudParameter.hash : '#',
                                        search: uplusApicloudParameter.search ? "?" + uplusApicloudParameter.search : '?',
                                    },
                                });
                                return;
                            }
                        }
                        finally {
                        }
                    }
                }
                resolve({
                    retCode: '',
                    retInfo: 'window.api.appParam is not defined!!!',
                    retData: {
                        hash: window.location.hash || '',
                        search: window.location.search || '',
                    },
                });
            });
        };
        ApicloudClass.prototype.getStatusBarHeight = function () {
            return new Promise(function (resolve, reject) {
                if (window.api.require('UpBaseModule')) {
                    window.api.require('UpBaseModule').getStatusBarHeight(function (ret) {
                        if (ret && resolve) {
                            resolve(resultAdapter(ret));
                        }
                        else if (reject) {
                            reject(resultAdapter(ret));
                        }
                    });
                }
                else {
                    reject(resultAdapter({ retInfo: 'userEngine is not defined!!!' }));
                }
            });
        };
        ApicloudClass.prototype.addPauseEventListener = function (eventListener) {
            if (typeof eventListener === 'function') {
                var flag = false;
                for (var i = 0; i < this.pauseEventStack.length; i++) {
                    if (this.pauseEventStack[i] === eventListener) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    this.pauseEventStack.push(eventListener);
                }
            }
        };
        ApicloudClass.prototype.addResumeEventListener = function (eventListener) {
            if (typeof eventListener === 'function') {
                var flag = false;
                for (var i = 0; i < this.resumeEventStack.length; i++) {
                    if (this.resumeEventStack[i] === eventListener) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    this.resumeEventStack.push(eventListener);
                }
            }
        };
        ApicloudClass.prototype.getDeviceInfoByMac = function (params) {
            var _this = this;
            var self = this;
            return new Promise(function (resolve, reject) { return __awaiter(_this, void 0, void 0, function () {
                var requestBody, userInfo, timestamp, signCode, response, error_1;
                return __generator(this, function (_a) {
                    switch (_a.label) {
                        case 0:
                            _a.trys.push([0, 4, , 5]);
                            if (!params || !params.deviceId) {
                                throw Error('deviceId 不允许为空');
                            }
                            requestBody = {
                                deviceId: params.deviceId || '',
                            };
                            return [4 /*yield*/, self.userModule.getUserInfo()];
                        case 1:
                            userInfo = _a.sent();
                            timestamp = (+new Date()).toString();
                            return [4 /*yield*/, self.userModule.getSignCode({
                                    data: JSON.stringify(requestBody),
                                    timestamp: timestamp,
                                })];
                        case 2:
                            signCode = _a.sent();
                            // http 请求
                            // 优先用 签名返回的 body
                            if (signCode && signCode.retData && signCode.retData.body) {
                                try {
                                    requestBody = JSON.parse(signCode.retData.body);
                                }
                                catch (_b) { }
                            }
                            return [4 /*yield*/, self.httpModule.post({
                                    url: 'http://uhome.haier.net:7500/emuplus/device/v3.0/detail',
                                    data: requestBody,
                                    headers: {
                                        // app信息
                                        appId: userInfo.retData.appId || '',
                                        appKey: userInfo.retData.appKey || '',
                                        appName: userInfo.retData.appName || '',
                                        appVersion: userInfo.retData.appVersion || '',
                                        clientId: userInfo.retData.clientId || '',
                                        // 登录信息
                                        accessToken: userInfo.retData.accessToken || '',
                                        userId: userInfo.retData.userId || '',
                                        // 签名信息
                                        sign: signCode.retData.sign || '',
                                        timestamp: timestamp,
                                        'Content-Type': 'application/json;charset=utf-8',
                                    },
                                })];
                        case 3:
                            response = _a.sent();
                            // 返回值
                            resolve(response);
                            return [3 /*break*/, 5];
                        case 4:
                            error_1 = _a.sent();
                            reject(error_1);
                            return [3 /*break*/, 5];
                        case 5: return [2 /*return*/];
                    }
                });
            }); });
        };
        ApicloudClass.prototype._initUplusTrace = function () {
            var self = this;
            document.removeEventListener('click', this._uplusTraceHandler.bind(self));
            document.addEventListener('click', this._uplusTraceHandler.bind(self));
        };
        ApicloudClass.prototype._uplusTraceHandler = function (event) {
            var elms = event.path || event.composedPath();
            var dataset = getDataSet(elms);
            if (dataset) {
                this.upTraceModule.reportPageClickEvent({ actionCode: dataset.id, extentInfo: dataset.data });
                if (typeof window.consoleLog === 'function') {
                    window.consoleLog(dataset.id);
                }
            }
        };
        return ApicloudClass;
    }());

    // 根据容器不同，返回对应的实例
    var baseClass = new ApicloudClass();
    if (window && window.navigator && window.navigator.userAgent && window.navigator.userAgent.indexOf('UpHybrid') !== -1) {
        baseClass = new CordovaClass();
    }
    // constructor 返回的是同一实例
    var UplusApi = /** @class */ (function (_super) {
        __extends(UplusApi, _super);
        function UplusApi(storeEngine) {
            var _this = _super.call(this) || this;
            baseClass.logicEngineModule.storeEngine = storeEngine;
            return baseClass;
        }
        return UplusApi;
    }(baseClass.constructor));

    return UplusApi;

})));
