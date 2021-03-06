//
//  FERelyUserRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERelyUserRequest.h"
#import "FEAttribute.h"
#import "FEPage.h"

@implementation FERelyUserRequest

-(id)initWithUserID:(NSNumber *)uid page:(FEPage *)page attributes:(NSArray *)attrs method:(NSString *)method{
    self = [super initWithMothed:method];
    if (self) {
        _userID = uid;
        _page = page;
        _filterList = attrs;
    }
    return self;
}

-(id)initWithUserID:(NSNumber *)uid page:(FEPage *)page attributes:(NSArray *)attrs{
    self = [super initWithMothed:@""];
    return self;
}

@end
