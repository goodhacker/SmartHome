//
//  FEBaseResponse.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
@class FEResult;

@interface FEBaseResponse : NSObject

@property (nonatomic, strong, readonly) FEResult *result;
-(id)initWithResult:(FEResult *)result;

@end