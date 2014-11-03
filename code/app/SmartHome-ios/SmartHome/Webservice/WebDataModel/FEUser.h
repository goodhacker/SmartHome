//
//  FEUser.h
//  SmartHome
//
//  Created by Seven on 14-11-3.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDictionaryObject.h"

@interface FEUser : FEDictionaryObject
@property (nonatomic, strong, readonly) NSArray *listAttr;
@property (nonatomic, strong, readonly) NSNumber *role;
@property (nonatomic, strong, readonly) NSNumber *userID;
@property (nonatomic, strong, readonly) NSNumber *userName;


@end
