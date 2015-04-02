//
//  FEMemoryCache.h
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FEUser.h"

@interface FEMemoryCache : NSObject
@property (nonatomic, strong) FEUser *user;

+(FEMemoryCache *)sharedInstance;

@end
