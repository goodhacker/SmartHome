//
//  FENewsRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FENewsRequest.h"

@implementation FENewsRequest


-(id)initWithPage:(FEPage *)page filter:(NSArray *)filer{
    self = [super initWithMothed:__METHOD_GET_NEWS];
    if (self) {
        _page = page;
        _filterList = filer;
    }
    return self;
}

@end
