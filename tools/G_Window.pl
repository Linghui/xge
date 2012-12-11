#!/usr/bin/perl -w

use strict;
use warnings;

use GJava;
use JSON;
use Getopt::Std;


#my $config_config = $GCTXC->convert_to_java($scene_config);

#my $json_text = to_json($scene_config);
#print $json_text;
#print "\n";

#use Data::Dumper qw/Dumper/;
#print Dumper($scene_config);

my $class_output_dir = 'classes/';

my %opts = ();

# options
# h: help
# f: for file or stdin input
# none: for command line string input
getopts('hf', \%opts);

if( $opts{h} ){
    &usage();
    exit 0;
}

#my $input = undef;
#
#if( $opts{f} ){
#    my @all_lines = <>;
#    chomp @all_lines;
#    $input = join '', @all_lines;
#} else {
#    
#    if(!defined($ARGV[0])){
#        print STDERR "Need json config string input\n";
#        &usage();
#        exit 1;
#    }
#    $input = $ARGV[0];
#}
#
#$scene_config = from_json($input);

my $gJava = GJava->new();


my $WINDOW_CLASS = 'window_class';
my $TEXT_TITLE   = 'text_title';
# output window class
my $window_config = {
    $WINDOW_CLASS => 'TestWindow',
    $TEXT_TITLE  => 'test',
};

my $window_class = $gJava->generate_window($window_config);
open WRT, "> $class_output_dir".$window_config->{$WINDOW_CLASS}.".java"
or die "open file error $!";

print WRT "$window_class";

close WRT;

sub usage(){

    print <<EOF;
Usage:
    perl $0 [-h | -f \$file_name/STDIN | \$json_string]
    -h: help
    -f: file or STDIN input mode
        example: perl $0 -f config_demo.json
    no options: for command json string input
    
EOF
}

sub HELP_MESSAGE(){
    &usage();
    exit 0;
}
