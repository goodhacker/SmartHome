//
//  FEMarkRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEMarkRequest.h"
#import "FEUserMark.h"


@implementation FEMarkRequest

-(id)initWithCommand:(NSString *)cmd usermark:(FEUserMark *)mark{
    self = [super initWithMothed:@""];
    if (self) {
        _command = cmd;
        _userMark = mark.dictionary;
    }
    return self;
}

@end
