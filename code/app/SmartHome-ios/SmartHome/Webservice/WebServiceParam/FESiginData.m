//
//  FESiginData.m
//  SmartHome
//
//  Created by Seven on 14-10-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESiginData.h"

@implementation FESiginData


-(instancetype)initWtihUserName:(NSString *)name password:(NSString *)password clientType:(NSString *)type clientVersion:(NSString *)version{
    self = [super initWithMothed:__MODTHED_SIGIN];
    if (self) {
        _userName = name;
        _passWord = password;
        _clientType = type;
        _clientVersion = version;
    }
    return self;
}

@end
