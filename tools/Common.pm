#!/usr/bin/perl -w
# Common key value

package Common;

use strict;
use warnings;

use Exporter 'import'; 

our @EXPORT = qw/$CLASS_NAME $EXTENDS_KEY $IMPLEMENTS_KEY/;

my $CLASS_NAME      = 'class_name';
my $EXTENDS_KEY     = 'extends';
my $IMPLEMENTS_KEY  = 'implements';

my $BACKGROUND_IMG_KEY  = 'background';

1
